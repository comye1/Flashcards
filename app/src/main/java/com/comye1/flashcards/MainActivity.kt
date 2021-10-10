package com.comye1.flashcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.comye1.flashcards.navigation.BottomNavigationBar
import com.comye1.flashcards.navigation.Screen
import com.comye1.flashcards.screens.CreateScreen
import com.comye1.flashcards.screens.DeckScreen
import com.comye1.flashcards.screens.HomeScreen
import com.comye1.flashcards.screens.MoreScreen
import com.comye1.flashcards.ui.theme.FlashcardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashcardsTheme {

                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) {

                    NavHost(navController = navController, startDestination = Screen.Home.route) {
                        composable(Screen.Home.route) {
                            HomeScreen(navController)
                        }
                        composable(Screen.Search.route) {
                            SearchScreen(navController)
                        }
                        composable(Screen.Create.route) {
                            CreateScreen(navController)
                        }
                        composable(Screen.More.route) {
                            MoreScreen(navController)
//                            TestScreen()
                        }
//                        composable("DeckScreen"){
//                            DeckScreen(navController = navController)
//                        }
                        composable(
                            "DeckScreen/{deckTitle}/{cardsNum}/{card1front}/{card1back}/{card2front}/{card2back}",
                            arguments = listOf(
                                navArgument("deckTitle") {
                                    type = NavType.StringType
                                },
                                navArgument("cardsNum") {
                                    type = NavType.IntType
                                },
                                navArgument("card1front") {
                                    type = NavType.StringType
                                },
                                navArgument("card1back") {
                                    type = NavType.StringType
                                },
                                navArgument("card2front") {
                                    type = NavType.StringType
                                },
                                navArgument("card2back") {
                                    type = NavType.StringType
                                },
                            )
                        ) { backStackEntry ->
                            val deckTitle = backStackEntry.arguments?.getString("deckTitle")
                            val cardsNum = backStackEntry.arguments?.getInt("cardsNum")
                            val card1front = backStackEntry.arguments?.getString("card1front")
                            val card1back = backStackEntry.arguments?.getString("card1back")
                            val card2front = backStackEntry.arguments?.getString("card2front")
                            val card2back = backStackEntry.arguments?.getString("card2back")
                            DeckScreen(
                                navController,
                                deckTitle,
                                cardsNum,
                                card1front,
                                card1back,
                                card2front,
                                card2back
                            )
                        }
                    }
                }
            }
        }
    }
}

/*
Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            IconButton(onClick = { navController.navigate("HomeScreen") }) {
                                Icon(
                                    imageVector = Icons.Outlined.Home,
                                    contentDescription = "home",
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                            IconButton(onClick = { navController.navigate("SearchScreen") }) {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "search",
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                            IconButton(onClick = { navController.navigate("CreateScreen") }) {
                                Icon(
                                    imageVector = Icons.Outlined.AddBox,
                                    contentDescription = "create",
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                            IconButton(onClick = { navController.navigate("MoreScreen") }) {
                                Icon(
                                    imageVector = Icons.Outlined.Menu,
                                    contentDescription = "more",
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                        }
 */

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    Column() {
        FindFlashCards({})
    }

}

@Composable
fun DeckInSubject() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray
            )
            .clickable {

            }
            .padding(16.dp)) {
        Text(
            text = "recursion",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "8 Cards",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

@Composable
fun StudyGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray
            )
            .clickable {

            }
            .padding(16.dp)) {
        Text(
            text = "c-plus-plus",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "12 Decks · 207 Cards",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

//@Composable
//fun DeckItem() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .border(
//                width = 2.dp,
//                color = Color.LightGray
//            )
//            .clickable {
//
//            }
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "recursion",
//            style = MaterialTheme.typography.h5,
//            fontWeight = FontWeight.Bold
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = "8 Cards",
//                style = MaterialTheme.typography.subtitle1,
//                fontWeight = FontWeight.Bold,
//                color = Color.Gray
//            )
//            Icon(
//                imageVector = Icons.Default.Bookmark,
//                contentDescription = "bookmark",
//                tint = Color.Gray
//            )
//        }
//    }
//}


@Composable
fun MyDeckItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray
            )
            .clickable {

            }
            .padding(16.dp)) {
        Text(
            text = "recursion",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "11 Cards",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Icon(
                imageVector = Icons.Default.VisibilityOff,
                contentDescription = "visibility_off",
                tint = Color.Gray
            )
        }

    }
}


@Composable
fun CardItem(front: String, back: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.LightGray)
    ) {
        Text(
            text = front,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.ExtraBold
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp), color = Color.LightGray
        )
        Text(
            text = back,
            modifier = Modifier.padding(16.dp),
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}


//@Composable
//fun CardItem(card: Card) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .border(width = 2.dp, color = Color.LightGray)
//    ) {
//        Text(
//            text = card.front,
//            modifier = Modifier.padding(16.dp),
//            fontWeight = FontWeight.ExtraBold
//        )
//        Divider(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(2.dp), color = Color.LightGray
//        )
//        Text(
//            text = card.back,
//            modifier = Modifier.padding(16.dp),
//            color = Color.Gray,
//            fontWeight = FontWeight.Bold
//        )
//    }
//}
