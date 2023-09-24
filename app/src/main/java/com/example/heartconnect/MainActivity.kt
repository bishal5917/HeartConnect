package com.example.heartconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeViewModel
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.navigation.Navigation
import com.example.heartconnect.ui.theme.HeartConnectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeartConnectTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        Navigation()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HeartConnectTheme {}
}