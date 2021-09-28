package com.comye1.flashcards.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.flashcards.DeckItem
import com.comye1.flashcards.FilterText
import com.comye1.flashcards.SampleDataSet
import com.comye1.flashcards.models.DECK_CREATED
import com.comye1.flashcards.ui.theme.DeepOrange

@Preview
@Composable
fun HomeScreen() {

    var (selectedFilterIndex, setFilterIndex) = remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 4.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            ) {
                Text(
                    "CheggPrep",
                    style = MaterialTheme.typography.h5,
                    color = DeepOrange,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                FilterSection(selectedFilterIndex, setFilterIndex)
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {

            when (selectedFilterIndex) {
                0 ->
                    SampleDataSet.deckSample.forEach {
                        item {
                            DeckItem(deck = it, modifier = Modifier.padding(bottom = 8.dp))
                        }
                    }
                1 ->
                    SampleDataSet.deckSample.filter { it.bookmarked }.forEach {
                        item {
                            DeckItem(deck = it, modifier = Modifier.padding(bottom = 8.dp))
                        }
                    }
                2 ->
                    SampleDataSet.deckSample.filter { it.deckType == DECK_CREATED }.forEach {
                        item {
                            DeckItem(deck = it, modifier = Modifier.padding(bottom = 8.dp))
                        }
                    }
            }

//                repeat (10) {
//                    item {
//                        DeckItem()
//                    }
//                    item {
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//                }
//                repeat (10) {
//                    item {
//                        MyDeckItem()
//                    }
//                    item {
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//                }
//                item {
//                    MakeMyDeck()
//                }
        }
    }

}

@Composable
fun FilterSection(selectedFilterIndex: Int, setIndex: (Int) -> Unit) {
    Row {
        FilterText("All", selectedFilterIndex == 0) { setIndex(0) }
        Spacer(modifier = Modifier.width(8.dp))
        FilterText("Bookmarks", selectedFilterIndex == 1) { setIndex(1) }
        Spacer(modifier = Modifier.width(8.dp))
        FilterText("Created", selectedFilterIndex == 2) { setIndex(2) }
    }
}
