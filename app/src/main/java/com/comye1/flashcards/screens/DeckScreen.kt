package com.comye1.flashcards.screens

import androidx.compose.foundation.background
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
import com.comye1.flashcards.CardItem
import com.comye1.flashcards.ui.theme.DeepOrange

@Composable
fun DeckScreen(
    navController: NavController,
    deckTitle: String?,
    cardsNum: Int?,
    card1front: String?,
    card1back: String?,
    card2front: String?,
    card2back: String?
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
            CardItem(card1front!!, card1back!!)
            Spacer(modifier = Modifier.height(8.dp))
            CardItem(card2front!!, card2back!!)
        }
    }
}

@Composable
fun BottomButton() {


}