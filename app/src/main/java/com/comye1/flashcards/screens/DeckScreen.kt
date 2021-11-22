package com.comye1.flashcards.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comye1.flashcards.models.Card
import com.comye1.flashcards.ui.theme.DeepOrange
import com.comye1.flashcards.viewmodels.CheggViewModel

@Composable
fun DeckScreen(
    navController: NavController,
    viewModel: CheggViewModel,
    deckId: String?,
) {
    val deck = deckId?.let {
        viewModel.getDeckById(it)
    }?: null

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
                actions = {
                    // TODO 북마크
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = "bookmark this deck"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Bookmark,
                            contentDescription = "bookmark this deck"
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
                    Text(text = deck?.deckTitle?: "오류", style = MaterialTheme.typography.h6)
                }
            )
        },
        bottomBar = {
            Column(modifier = Modifier.background(color = Color.White)){
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
        if(deck != null) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = deck.cardList.size.toString() + if (deck.cardList.size > 1) " Cards" else "Card",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                deck.cardList.forEach {
                    CardItem(it)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun BottomButton() {


}


@Composable
fun CardItem(card: Card) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.LightGray)
    ) {
        Text(
            text = card.front,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.ExtraBold
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp), color = Color.LightGray
        )
        Text(
            text = card.back,
            modifier = Modifier.padding(16.dp),
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}

