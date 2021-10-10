package com.comye1.flashcards.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.comye1.flashcards.ui.theme.DeepOrange

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(Screen.Home.route, "Home", Icons.Outlined.Home),
        BottomNavItem(Screen.Search.route, "Search", Icons.Outlined.Search),
        BottomNavItem(Screen.Create.route, "Create", Icons.Outlined.AddBox),
        BottomNavItem(Screen.More.route, "More", Icons.Outlined.Menu),
    )
    // observe the backstack
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier.scale(1.3f),
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.name) },
                label = { Text(text = item.name) },
//                alwaysShowLabel = true,
                selectedContentColor = DeepOrange,
                unselectedContentColor = Color.DarkGray
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val name: String,
    val icon: ImageVector
)

