package com.example.heartconnect.features.presentation.screens.profile.components.my_pic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.heartconnect.R
import com.example.heartconnect.composables.CustomAppbar
import com.example.heartconnect.composables.CustomNetworkImage
import com.example.heartconnect.features.presentation.screens.profile.viewmodel.ProfileViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPicsScreen(
    navController: NavController, profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileState = profileViewModel.profileState.collectAsState()

    Scaffold(topBar = {
        CustomAppbar(navController,
            title = stringResource(id = R.string.my_pictures),
            actionButtonClicked = {})
    }) {
        //Show uploaded images grid
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(), cells = GridCells.Fixed(2)
        ) {
            items(profileState.value.user?.pics ?: emptyList()) { pic ->
                CustomNetworkImage(
                    imageUrl = pic,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    parentmodifier = Modifier.size(200.dp)
                )
            }
        }
    }
}