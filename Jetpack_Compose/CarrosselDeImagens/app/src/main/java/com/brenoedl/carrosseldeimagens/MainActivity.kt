package com.brenoedl.carrosseldeimagens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@Composable
fun Home() {
    val pagerItems = listOf(
        R.drawable.foto1,
        R.drawable.foto2,
        R.drawable.foto3,
        R.drawable.foto4,
        R.drawable.foto5,
        R.drawable.foto6,
        R.drawable.foto7,
        R.drawable.foto8
    )

    val pagerState = rememberPagerState {
        pagerItems.size
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            contentPadding = PaddingValues(32.dp),
        ) { page ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = pagerItems[page]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f).padding(5.dp)
                        .clip(
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f).padding(10.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = "${page + 1}",
                        modifier = Modifier.background(
                            color = Color.White,
                            shape = CircleShape
                        ).size(30.dp).padding(0.dp, 5.dp, 0.dp, 0.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) {

                val color = if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray

                Box(
                    modifier = Modifier.padding(2.dp)
                        .clip(CircleShape).background(color = color).size(16.dp)
                ) {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}