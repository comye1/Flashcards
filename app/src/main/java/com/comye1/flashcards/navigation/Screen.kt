package com.comye1.flashcards.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Search: Screen("search")
    object Create: Screen("create")
    object More: Screen("more")
}