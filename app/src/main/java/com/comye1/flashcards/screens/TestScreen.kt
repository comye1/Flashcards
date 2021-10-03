package com.comye1.flashcards.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.flashcards.ui.theme.DeepOrange

// 전체
@Preview
@Composable
fun TestScreen() {
    val totCardNumber by remember {
        mutableStateOf(10)
    }

    val (curNum, setcurNum) = remember {
        mutableStateOf(0)
    }

    var animationPlayed by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = curNum) {
        animationPlayed = true
    }
    // 안된다 ㅋㅋㅋㅋㅋ
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("${curNum + 1} / $totCardNumber")
        QuizProgress(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .height(12.dp),
            animationPlayed = animationPlayed,
            curNum = curNum,
            totNum = totCardNumber
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { setcurNum(curNum + 1) }) {
            Text("다음 카드로 넘어가기")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Current value = $curNum")
        if(curNum == totCardNumber - 1){
            Text("끝")
        }
    }


}

@Composable
fun QuizProgress(modifier: Modifier, animationPlayed: Boolean, curNum: Int, totNum: Int) {
    val start = curNum / totNum.toFloat()
    val end = (curNum + 1) / totNum.toFloat()
    val curValue = animateFloatAsState(
        targetValue = if (animationPlayed) end else start,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 0
        )
    )

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curValue.value)
                .background(color = DeepOrange)
                .padding(8.dp)
        ){}
    }
}