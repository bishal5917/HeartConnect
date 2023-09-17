package com.example.heartconnect.features.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.features.presentation.screens.home.components.CardItem
import com.example.heartconnect.features.presentation.screens.home.components.HomeCard
import com.example.heartconnect.ui.theme.VSizedBox0
import com.example.heartconnect.ui.theme.VSizedBox1
import com.example.heartconnect.ui.theme.VSizedBox4

@Composable
fun HomeScreen(navController: NavController) {
    val cardItems: List<CardItem> = listOf(
        CardItem(
            name = "Johnny Depp", age = "55", painterResource(
                id = R.drawable.tiger
            ), hobbies = listOf("Hiking", "Coding", "Travelling", "Music")
        ),
        CardItem(
            name = "Tim Berg", age = "23", painterResource(
                id = R.drawable.lion
            ), hobbies = listOf("Music", "Coding", "Hiking", "Fl-Studio")
        ),
        CardItem(
            name = "Johnny Depp", age = "55", painterResource(
                id = R.drawable.tiger
            ), hobbies = listOf("Hiking", "Coding", "Travelling", "Music")
        ),
        CardItem(
            name = "Tim Berg", age = "23", painterResource(
                id = R.drawable.lion
            ), hobbies = listOf("Music", "Coding", "Hiking", "Fl-Studio")
        )
    )

    Surface(
        modifier = Modifier.padding(10.dp),
    ) {
        LazyColumn {
            items(cardItems) { cardItem ->
                HomeCard(cardItem)
                VSizedBox1()
            }
        }
    }
}