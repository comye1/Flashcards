package com.comye1.Flashcards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.TurnedIn
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.Flashcards.ui.theme.DeepOrange
import org.w3c.dom.Text

@Preview
@Composable
fun FirstWeekPreview() {
    Column() {
//        DeckInSubject()
        StudyGuide()
        DeckItem()
        MyDeckItem()
        MakeMyDeck()
        SubjectItem()
        CardItem()
        Row() {
            FilterText(Color.Transparent)
            FilterText(Color.LightGray)
            FilterText(Color.Transparent)

        }
    }

}

// Column, fillMaxWidth(), padding, border 적용
// Text에 style, fontWeight 설정하면서..
// Spacer로 간격 띄우기 (padding보다는??!!)
// padding 없애고 복붙하기 ㅠㅠ
@Composable
fun DeckInSubject() {
    // 각 Subject 화면 내
    Column(
        modifier = Modifier
            .fillMaxWidth()// 1.
            .padding(8.dp)//5. 그림 설명
            .clickable {//8.

            } //나중에 => 클릭하고 싶은 부분!!
            .border(2.dp, Color.LightGray)//6.
            .padding(16.dp)//7.,
    ) {
        Text( //2.
            text = "recursion",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(4.dp)) //4.
        Text( //3.
            text = "8 Cards",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

// 복붙, 실습
@Composable
fun StudyGuide() {
    // 각 Subject 화면 내
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {

            }
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

// 복붙, Row를 사용해보자!
// Row내 요소들 배치 방법 -
// IconButton 말고 Icon만 사용하기
// Icon 추가 라이브러리
@Composable
fun DeckItem() {
    // Home 화면 내
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {

            }
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

// 복붙하세요, 아이콘 찾아서 교체하기
@Composable
fun MyDeckItem() {
    // Home 화면 내
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {

            }
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

// 일단 복붙하고 텍스트 추가, Row 배치를 약간 다르게
// Start로 배치하면 되는데 이건 사실 디폴트
@Composable
fun MakeMyDeck() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {

            }
            .border(2.dp, Color.LightGray)
            .padding(20.dp),
    ) {
        Text(
            text = "Make your own cards",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "It's easy to create your own flashcard deck -for free.",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
//            IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.NoteAdd,
                contentDescription = "add_deck",
                tint = Color.Blue
            )
//            } IconButton 인 줄 알았더니 아무거나 클릭해도 되는거였어!!
//            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = " Get started",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}

// 간단하게 하나의 Row만 있으면 됨!
// border에 shape 적용
// clip도 있음!!
// Row 자체에 모양을 적용하려면 clip
// click할 수 있는 범위
@Composable
fun SubjectItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.LightGray, shape = RoundedCornerShape(size = 8.dp))
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .clickable {

            }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Computer,
            contentDescription = "computer science",
            tint = DeepOrange,
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Computer Science",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

// 일단 Column을 쓴걸로 예상됨!
// Divider 소개
// Text에 padding을 주는 수밖에..!
@Composable
fun CardItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.LightGray),
    ) {
        Text(
            "Operating Systems",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.ExtraBold
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = Color.LightGray
        )
        Text(
            "A request to execute an OS service-layer function",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

// click 못하게 만들기 -> enabled 속성 (이미 클릭된 아이템)
// 바깥 padding 안 넣은 이유
@Composable
fun FilterText(backgroundColoc: Color) {
    Box(modifier = Modifier
        .padding(start = 0.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
        .clip(shape = CircleShape)
        .clickable(enabled = false) { }
        .background(color = backgroundColoc)
        .padding(horizontal = 20.dp, vertical = 4.dp)
    ) {
        Text("All", style = MaterialTheme.typography.body1, fontWeight = FontWeight.ExtraBold)
    }
}