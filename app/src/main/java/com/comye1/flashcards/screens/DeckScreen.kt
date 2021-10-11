package com.comye1.flashcards.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comye1.flashcards.ui.theme.DeepOrange

@Composable
fun DeckScreen(
    navController: NavController,
    deckTitle: String?,
    cardsNum: Int?,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "share this deck"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.MoreVert,
                            contentDescription = "more actions"
                        )
                    }
                },
                title = {
                    Text(text = deckTitle!!, style = MaterialTheme.typography.h6)
                }
            )
        },
        bottomBar = {
            Column {
                Divider(Modifier.height(2.dp), color = Color.LightGray)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clickable { }
                            .background(color = DeepOrange)
                            .padding(horizontal = 24.dp, vertical = 8.dp)

                    ) {
                        Text(
                            text = "Practice all cards",
                            color = Color.White,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }

        }
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = cardsNum!!.toString() + if (cardsNum!! > 1) " Cards" else "Card", color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            repeat(5) {
                CardItem(front = "abcdefg", back = "hijklmnopqrstuvwxyz")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun BottomButton() {


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

