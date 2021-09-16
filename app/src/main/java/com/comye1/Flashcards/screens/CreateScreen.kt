package com.comye1.Flashcards.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comye1.Flashcards.DeckTitleTextField

@Composable
fun CreateScreen() {

    val canGoNext by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text("Create new deck", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            actions = {
                // RowScope here, so these icons will be placed horizontally
                TextButton(onClick = { /*TODO*/ }, enabled = canGoNext ) {
                    Text("Next", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
    ) {
        Column (modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center){
            DeckTitleTextField()
        }
    }
}

@Composable
fun CreateScreenTopBar() {

}