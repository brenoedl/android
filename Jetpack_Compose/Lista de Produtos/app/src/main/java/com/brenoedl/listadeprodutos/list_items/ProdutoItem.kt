package com.brenoedl.listadeprodutos.list_items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.listadeprodutos.R
import com.brenoedl.listadeprodutos.model.Produto
import com.brenoedl.listadeprodutos.ui.theme.Red
import com.brenoedl.listadeprodutos.ui.theme.White

@Composable
fun ProdutoItem(produto: Produto) {

    Spacer(modifier = Modifier.padding(10.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = White,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = produto.img),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = produto.nome,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
        }

        Text(
            text = produto.preco,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Red,
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "A vista no PIX",
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center
        )

        OutlinedButton(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Red
            ),
            modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Comprar",
                fontSize = 16.sp,
                color = White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProdutoItemPreview() {
    ProdutoItem(
        produto = Produto(
            nome = "Caneta",
            img = R.drawable.img5,
            preco = "R$ 2369,93"
        )
    )
}