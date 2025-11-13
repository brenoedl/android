package com.brenoedl.appdeidiomas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.appdeidiomas.ui.theme.Blue900
import com.brenoedl.appdeidiomas.ui.theme.Gray900
import com.brenoedl.appdeidiomas.ui.theme.White

class MainActivity : ComponentActivity() {
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
            Image(
                painter = painterResource(id = R.drawable.bandeira1),
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
        Home()
}