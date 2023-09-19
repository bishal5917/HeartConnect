package com.example.heartconnect.features.presentation.screens.home.components

import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.heartconnect.components.CustomNetworkImage
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.features.data.models.feed.FeedModel
import com.example.heartconnect.ui.theme.VSizedBox0

@Composable
fun HomeCard(cardItem: FeedModel) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp)),
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            CustomNetworkImage(
                imageUrl = cardItem.profileImage ?: "", modifier = Modifier
                    .fillMaxSize(), parentmodifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            VSizedBox0()
            CustomText(
                data = "${cardItem.name} , ${cardItem.birthYear}",
                fontWeight = FontWeight.W500,
                fontSize = 14
            )
            VSizedBox0()
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(cardItem.hobbies ?: listOf("")) { hobby ->
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
