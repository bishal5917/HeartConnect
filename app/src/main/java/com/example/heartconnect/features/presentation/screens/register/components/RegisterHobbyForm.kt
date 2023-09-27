package com.example.heartconnect.features.presentation.screens.register.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartconnect.composables.CustomText
import com.example.heartconnect.composables.CustomToast
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterEvent
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterState
import com.example.heartconnect.features.presentation.screens.register.viewmodel.register_viewmodel.RegisterViewModel
import com.example.heartconnect.ui.theme.Primary
import com.example.heartconnect.ui.theme.WhiteColor
import com.example.heartconnect.ui.theme.kNeutral800Color
import com.example.heartconnect.utils.RegisterUtil
import dagger.hilt.android.lifecycle.HiltViewModel

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
        "Coding",
        "Basketball",
        "Soccer",
        "Meditation",
        "Knitting",
        "Skydiving",
        "Rock Climbing",
        "Scuba Diving",
    )

    val registerViewModel = hiltViewModel<RegisterViewModel>()
    val registerState by registerViewModel.registerState.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),cells = GridCells.Fixed(3)
    ) {
        items(allHobbies) { hobby ->
            Box(modifier = Modifier.clickable {
                registerViewModel.onEvent(RegisterEvent.AddOrRemoveHobby(hobby))
            }) {
                HobbyItem(hobby, RegisterUtil().isAdded(registerState.hobbies!!, hobby))
            }
        }
    }
}

@Composable
fun HobbyItem(
    hobby: String, isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(
                if (isSelected) Primary else WhiteColor, shape = RoundedCornerShape(
                    12.dp
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        CustomText(
            data = hobby, modifier = Modifier.padding(8.dp),
            color = if (isSelected) WhiteColor else kNeutral800Color,
            fontWeight = FontWeight.W500, fontSize = 16,
        )
    }
}