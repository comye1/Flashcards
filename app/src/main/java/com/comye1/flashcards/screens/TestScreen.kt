package com.comye1.flashcards.screens

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.flashcards.ui.theme.DeepOrange

@Preview
@Composable
fun TestScreen() {
    val (count, setCount) = remember { // 현재 카드 수
        mutableStateOf(0f)
    }
    val totCount = 7 // 총 카드 수
    LaunchedEffect(key1 = true) { // 시작 효과 : 0->1
        setCount(1f)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        //TopBar 들어갈 텍스트
        Text(
            text = " ${count.toInt()} / $totCount",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 프로그레스바
        ProgressBar(count = count, totCount = totCount)

        Spacer(modifier = Modifier.height(16.dp))

        // 카드 넘기는 동작 일단 버튼으로 구현
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { if (count > 1) setCount(count - 1) }
            ) {
                Text("이전 카드")
            }
            Button(
                onClick = { if (count < totCount) setCount(count + 1) }
            ) {
                Text("다음 카드")
            }
        }
    }


}

