package com.comye1.flashcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comye1.flashcards.navigation.BottomNavigationBar
import com.comye1.flashcards.navigation.Screen
import com.comye1.flashcards.screens.*
import com.comye1.flashcards.ui.theme.FlashcardsTheme
import com.comye1.flashcards.viewmodels.CheggViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : ComponentActivity() {
    @InternalCoroutinesApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashcardsTheme {

                var (bottomBarShown, showBottomBar) = remember {
                    mutableStateOf(true)
                }

                val navController = rememberNavController()

                val cheggViewModel: CheggViewModel = viewModel()

                Scaffold(
                    bottomBar = {
                        if (bottomBarShown) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) {

                    NavHost(navController = navController, startDestination = Screen.Home.route) {
                        composable(Screen.Home.route) {
                            showBottomBar(true)
                            HomeScreen(navController, cheggViewModel)
                        }
                        composable(Screen.Search.route) {
                            showBottomBar(true)
                            SearchScreen(navController)
                        }
                        composable(Screen.Create.route) {
                            showBottomBar(false)
                            CreateScreen(navController)
                        }
                        composable(Screen.More.route) {
                            showBottomBar(true)
                            MoreScreen(navController)
//                            TestScreen()
                        }
//                        composable("DeckScreen"){
//                            DeckScreen(navController = navController)
//                        }
                        composable(
                            "DeckScreen/{deckTitle}/{cardsNum}",
                        ) { backStackEntry ->
                            val deckTitle = backStackEntry.arguments?.getString("deckTitle")
                            val cardsNum = backStackEntry.arguments?.getString("cardsNum")?.toInt()
                            showBottomBar(false)
                            DeckScreen(
                                navController,
                                deckTitle,
                                cardsNum
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
