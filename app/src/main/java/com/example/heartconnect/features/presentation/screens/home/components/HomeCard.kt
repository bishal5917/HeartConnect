package com.example.heartconnect.features.presentation.screens.home.components

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.heartconnect.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.features.presentation.screens.main.components.BottomBarScreen
import com.example.heartconnect.ui.theme.VSizedBox0
import com.example.heartconnect.ui.theme.VSizedBox2

data class CardItem(
    val name: String, val age: String, val imageResource: Painter, val hobbies: List<String>
)

@Composable
fun HomeCard(cardItem: CardItem) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp)),
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = cardItem.imageResource,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,

                )
            VSizedBox0()
            CustomText(
                data = "${cardItem.name},${cardItem.age}",
                fontWeight = FontWeight.W500,
                fontSize = 14
            )
            VSizedBox0()
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(cardItem.hobbies) { hobby ->
                    CustomText(
                        data = hobby,
                        fontWeight = FontWeight.W400,
                        fontSize = 12,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CardDemoPreview() {
    HomeCard(
        cardItem = CardItem(
            name = "Johnny Depp", age = "55", painterResource(
                id = R.drawable.tiger
            ), hobbies = listOf("Hiking", "Coding", "Travelling", "Music", "Guitaring")
        )
    )
}
