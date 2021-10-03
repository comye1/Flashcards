package com.comye1.flashcards.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comye1.flashcards.DeckTitleTextField
import com.comye1.flashcards.ui.theme.DeepOrange
import com.comye1.flashcards.ui.theme.LightOrange

@Composable
fun CreateScreen() {

    val (deckTitle, setDectTitle) = remember {
        mutableStateOf("")
    }

    val (visible, setVisibility) = remember {
        mutableStateOf(true)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Create new deck",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "close screen"
                    )
                }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            actions = {
                // RowScope here, so these icons will be placed horizontally
                TextButton(onClick = { /*TODO*/ }, enabled = !deckTitle.isNullOrEmpty()) {
                    Text("Next", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            DeckTitleTextField(deckTitle, setDectTitle)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Visible to everyone",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Switch(
                    checked = visible,
                    onCheckedChange = setVisibility,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = DeepOrange,
                        checkedTrackColor = LightOrange
                    )
                )
            }
            Text(
                "Other Students can find, view, and study\nthis deck"
            )
        }
    }
}

@Composable
fun CreateScreenTopBar() {

}