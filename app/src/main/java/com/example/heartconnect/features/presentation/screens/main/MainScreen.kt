package com.example.heartconnect.features.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.heartconnect.components.CustomText
import com.example.heartconnect.features.presentation.screens.main.components.BottomBarScreen
import com.example.heartconnect.features.presentation.screens.main.components.BottomNavGraph
import com.example.heartconnect.ui.theme.Primary

@Composable
fun MainScreen(navController: NavController) {
    val navvController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController = navvController) }) {
        BottomNavGraph(navController = navvController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Chat,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )

        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen, currentDestination: NavDestination?, navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            CustomText(data = screen.title, fontSize = 11, fontWeight = FontWeight.W400)
        },
        icon = {
            Icon(
                imageVector = screen.icon, contentDescription = "Navigation Icon",
                tint = Primary
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier.background(color = Color.White)
    )
}