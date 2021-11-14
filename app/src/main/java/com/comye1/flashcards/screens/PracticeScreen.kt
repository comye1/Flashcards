package com.comye1.flashcards.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Shuffle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comye1.flashcards.SampleDataSet
import com.comye1.flashcards.ui.theme.DeepOrange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun PracticeScreen() {

    val sampleData = SampleDataSet.myDeckSample[0].cardList
    //
    val (count, setCount) = remember { // 현재 카드 수
        mutableStateOf(0f)
    }
//    val totCount = 11 // 총 카드 수

    LaunchedEffect(key1 = true) { // 시작 효과 : 0 -> 1
        setCount(1f)
    }

    val pagerState = rememberPagerState() // Pager의 상태 (페이지 수, 현재 페이지 등)

    LaunchedEffect(key1 = pagerState.currentPage) {
        setCount((pagerState.currentPage + 1).toFloat())
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${pagerState.currentPage + 1}/${sampleData.size}",
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
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Shuffle,
                        contentDescription = "shuffle cards"
                    )
                }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
        )
    }
    ) {

        Column {
            // 프로그레스바
            Box(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
                ProgressBar(count = count, totCount = sampleData.size)
            }
            Box {
                var state by remember {
                    mutableStateOf(CardFace.Front)
                }

                HorizontalPager( // Pager
                    count = sampleData.size,
                    state = pagerState, // 선언한 pagerState 사용 (선언하지 않으면 내부에서 자동으로 사용)
                    contentPadding = PaddingValues(32.dp)
                    // 양쪽에 이전, 다음 카드를 보여줌
                ) { page ->

                    FlipCard(
                        cardFace = state, onClick = { state = it.next }, axis = RotationAxis.AxisY,
                        back = {
                            CardBack(text = sampleData[page].back)
                        },
                        front = {
                            CardFront(text = sampleData[page].front)
                        }
                    )
                }
            }
        }
    }
}

/////////////progressbar////////////
@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    color: Color = DeepOrange,
    animDuration: Int = 300,
    animDelay: Int = 0,
    count: Float,
    totCount: Int,
) {
    val curPercentage by animateFloatAsState(
        targetValue = count / totCount,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay,
            easing = LinearOutSlowInEasing
        )
    )
    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
            .clip(CircleShape),
        progress = curPercentage,
        color = color,
        backgroundColor = Color.LightGray
    )
}

/////////////flip card////////////
enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

enum class RotationAxis {
    AxisX,
    AxisY,
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    Column(Modifier.padding(16.dp)) {
        CardFront(text = "Iterations")
        CardBack(text = "Repeated steps in an SDLC process; for example, in the UP, each iteration, consisting of specific activities")
    }
}

@Composable
fun CardFront(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.h3)
    }
}

@Composable
fun CardBack(text: String) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = Color.LightGray)
            .verticalScroll(state = scrollState)
            .padding(16.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.h4)
    }
}

@ExperimentalMaterialApi
@Composable
fun FlipCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    axis: RotationAxis = RotationAxis.AxisY,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        )
    )
    Box(modifier = Modifier.padding(8.dp)) {
        Card(
            onClick = { onClick(cardFace) },
            modifier = modifier
                .graphicsLayer {
                    if (axis == RotationAxis.AxisX) {
                        rotationX = rotation.value
                    } else {
                        rotationY = rotation.value
                    }
                    cameraDistance = 12f * density
                },
        ) {
            if (rotation.value <= 90f) {
                Box(
                    Modifier.fillMaxSize()
                ) {
                    front()
                }
            } else {
                Box(
                    Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            if (axis == RotationAxis.AxisX) {
                                rotationX = 180f
                            } else {
                                rotationY = 180f
                            }
                        },
                ) {
                    back()
                }
            }
        }
    }
}