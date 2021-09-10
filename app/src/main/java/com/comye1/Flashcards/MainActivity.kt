package com.comye1.Flashcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TurnedIn
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.Flashcards.ui.theme.FlashcardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashcardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        DeckItem()
                        DeckInSubject()
                        StudyGuide()
                        MyDeckItem()
                    }
                }
            }
        }
    }
}

@Composable
fun DeckItem() {
    // Home 화면 내
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, Color.LightGray)
            .padding(16.dp),
    ) {
        Text(
            text = "recursion",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
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
                imageVector = Icons.Default.TurnedIn,
                contentDescription = "bookmarked",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun MyDeckItem() {
    // Home 화면 내
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, Color.LightGray)
            .padding(16.dp),
    ) {
        Text(
            text = "recursion",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
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
//            Icon(
//                imageVector = Icons.Default.Visibility,
//                contentDescription = "visibility_on",
//                tint = Color.Gray
//            )
        }
    }
}


@Composable
fun DeckInSubject() {
    // 각 Subject 화면 내
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, Color.LightGray)
            .padding(16.dp),
    ) {
        Text(
            text = "recursion",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
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
    // 각 Subject 화면 내
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(2.dp, Color.LightGray)
            .padding(16.dp),
    ) {
        Text(
            text = "c-plus-plus",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column() {
        DeckItem()
        DeckInSubject()
    }
}