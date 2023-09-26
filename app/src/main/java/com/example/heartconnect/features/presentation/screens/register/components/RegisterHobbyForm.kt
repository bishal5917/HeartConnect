package com.example.heartconnect.features.presentation.screens.register.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.heartconnect.composables.CustomText
import com.example.heartconnect.ui.theme.Primary
import com.example.heartconnect.ui.theme.WhiteColor
import com.example.heartconnect.ui.theme.kNeutral800Color

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterHobbyForm() {
    val allHobbies = listOf(
        "Reading",
        "Cooking",
        "Hiking",
        "Painting",
        "Gardening",
        "Photography",
        "Dancing",
        "Fishing",
        "Camping",
        "Traveling",
        "Swimming",
        "Biking",
        "Drawing",
        "Singing",
        "Writing",
        "Yoga",
        "Running",
        "Chess",
        "Video Games",
        "Programming",
        "Basketball",
        "Soccer",
        "Meditation",
        "Knitting",
        "Skydiving",
        "Rock Climbing",
        "Scuba Diving",
    )

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(), cells = GridCells.Fixed(3)
    ) {
        items(allHobbies) { hobby ->
            HobbyItem(hobby)
        }
    }
}

@Composable
fun HobbyItem(hobby: String) {

    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Primary, shape = RoundedCornerShape(12.dp))
            .clickable {
                //handle clickable
            },
    ) {
        CustomText(data = hobby, modifier = Modifier.padding(8.dp), color = kNeutral800Color,
            fontWeight = FontWeight.W400, fontSize = 16,)
    }
}