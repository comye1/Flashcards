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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                DeckScreen(navController = navController)

//                Scaffold(
//                    bottomBar = {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .background(Color.White)
//                                .padding(vertical = 12.dp),
//                            horizontalArrangement = Arrangement.SpaceAround
//                        ) {
//                            IconButton(onClick = { navController.navigate("HomeScreen") }) {
//                                Icon(
//                                    imageVector = Icons.Outlined.Home,
//                                    contentDescription = "home",
//                                    modifier = Modifier.size(60.dp)
//                                )
//                            }
//                            IconButton(onClick = { navController.navigate("SearchScreen") }) {
//                                Icon(
//                                    imageVector = Icons.Outlined.Search,
//                                    contentDescription = "search",
//                                    modifier = Modifier.size(60.dp)
//                                )
//                            }
//                            IconButton(onClick = { navController.navigate("CreateScreen") }) {
//                                Icon(
//                                    imageVector = Icons.Outlined.AddBox,
//                                    contentDescription = "create",
//                                    modifier = Modifier.size(60.dp)
//                                )
//                            }
//                            IconButton(onClick = { navController.navigate("MoreScreen") }) {
//                                Icon(
//                                    imageVector = Icons.Outlined.Menu,
//                                    contentDescription = "more",
//                                    modifier = Modifier.size(60.dp)
//                                )
//                            }
//                        }
//                    }
//                ) {
//
//                    NavHost(navController = navController, startDestination = "HomeScreen") {
//                        composable("HomeScreen") {
//                            HomeScreen(navController)
//                        }
//                        composable("SearchScreen") {
//                            SearchScreen(navController)
//                        }
//                        composable("CreateScreen") {
//                            CreateScreen(navController)
//                        }
//                        composable("MoreScreen") {
//                            MoreScreen(navController)
////                            TestScreen()
//                        }
//                    }
//                }
            }
        }
    }
}


//@Composable
//fun FilterText() {
//    Row(modifier = Modifier
//        .clip(shape = CircleShape)
//        .clickable(enabled = false) { }
//        .background(color = Color.LightGray)
//        .padding(horizontal = 20.dp, vertical = 2.dp)
//    ) {
//        Text("All", style = MaterialTheme.typography.body1, fontWeight = FontWeight.ExtraBold)
//    }
//}





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
fun CardItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.LightGray)
    ) {
        Text(
            text = "Operating Systems",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.ExtraBold
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp), color = Color.LightGray
        )
        Text(
            text = "A request to execute an OS service-layer function",
            modifier = Modifier.padding(16.dp),
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}
