package com.example.heartconnect.composables

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.heartconnect.core.navigation.Navigator
import com.example.heartconnect.ui.theme.Primary
import com.example.heartconnect.ui.theme.kNeutral300Color

@Composable
fun CustomAppbar(
    navController: NavController,
    title: String,
    actionButtonClicked: () -> Unit,
    showAction: Boolean = false,
) {

    TopAppBar(title = {
        CustomText(data = title, fontSize = 15, fontWeight = FontWeight.W400, color = Color.Gray)
    }, navigationIcon = {
        CustomIconButton(childIcon = Icons.Default.ArrowBack, contentDesc = "Back Icon") {
            Navigator().back(navController)
        }
    }, actions = {
        if (showAction)
            CustomIconButton(
                childIcon = Icons.Default.Info, contentDesc = "Info Icon", color = kNeutral300Color
            ) {
                actionButtonClicked()
            }
    })
}