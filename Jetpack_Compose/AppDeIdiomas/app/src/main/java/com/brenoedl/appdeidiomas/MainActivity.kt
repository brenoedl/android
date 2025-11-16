package com.brenoedl.appdeidiomas

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.brenoedl.appdeidiomas.datasource.LanguagePreferences
import com.brenoedl.appdeidiomas.ui.theme.Blue900
import com.brenoedl.appdeidiomas.ui.theme.Gray900
import com.brenoedl.appdeidiomas.ui.theme.White
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(){
    var espandirMenu by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf("") }
    var flag by remember { mutableIntStateOf(R.drawable.bandeira1) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        LanguagePreferences.getLanguage(context = context).collect {
            language = it
            setLanguage(context = context, languageCode = language)
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue900
                ),
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .clickable {
                                espandirMenu = true
                            }
                            .padding(16.dp)
                    )

                    Column(
                        modifier = Modifier
                    ) {
                        DropdownMenu(
                            expanded = espandirMenu,
                            onDismissRequest = { espandirMenu = false },
                            modifier = Modifier
                                .width(200.dp)
                                .background(White)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Português")
                                },
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.brasil),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        LanguagePreferences.saveLanguage(context = context, languageCode = "pt")
                                        espandirMenu = false
                                    }
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(text = "Inglês")
                                },
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.eua),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        LanguagePreferences.saveLanguage(context = context, languageCode = "en")
                                        espandirMenu = false
                                    }
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(text = "Espanhol")
                                },
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.espanha),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        LanguagePreferences.saveLanguage(context = context, languageCode = "es")
                                        espandirMenu = false
                                    }
                                }
                            )
                        }
                    }
                }
            )
        }
    ){paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().background(White).padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            when(language){
                "pt" -> flag = R.drawable.bandeira1
                "en" -> flag = R.drawable.bandeira2
                "es" -> flag = R.drawable.bandeira3
            }

            Image(
                painter = painterResource(id = flag),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = stringResource(id = R.string.idioma),
                fontSize = 40.sp,
                color = Gray900,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.descricao),
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally)
                    .border(2.dp, Blue900).padding(10.dp)
            )
        }
    }
}

private fun setLanguage(context: Context, languageCode: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        context.getSystemService(LocaleManager::class.java)
            .applicationLocales = LocaleList.forLanguageTags(languageCode)
    }else{
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
        Home()
}