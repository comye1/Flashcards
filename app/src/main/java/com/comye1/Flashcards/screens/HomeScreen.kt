package com.comye1.Flashcards.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.Flashcards.DeckItem
import com.comye1.Flashcards.FilterText
import com.comye1.Flashcards.MakeMyDeck
import com.comye1.Flashcards.MyDeckItem
import com.comye1.Flashcards.ui.theme.DeepOrange

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    "CheggPrep",
                    style = MaterialTheme.typography.h5,
                    color = DeepOrange,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row() {
                    FilterText()
                    Spacer(modifier = Modifier.width(16.dp))
                    FilterText()
                    Spacer(modifier = Modifier.width(16.dp))
                    FilterText()
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn {
                repeat (10) {
                    item {
                        DeckItem()
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                repeat (10) {
                    item {
                        MyDeckItem()
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                item {
                    MakeMyDeck()
                }
            }
        }
    }
}