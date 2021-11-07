package com.comye1.flashcards.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.comye1.flashcards.models.Card
import com.comye1.flashcards.ui.theme.DeepOrange
import com.comye1.flashcards.ui.theme.LightOrange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

enum class CreateScreen {
    TitleScreen, // 0
    CardScreen // 1
}

@ExperimentalPagerApi
@Composable
fun CreateScreen(navController: NavHostController) {

    val (deckTitle, setDeckTitle) = remember {
        mutableStateOf("")
    }

    val (visible, setVisibility) = remember {
        mutableStateOf(true)
    }

    val (screenState, setScreenState) = remember {
        mutableStateOf(CreateScreen.TitleScreen)
    }

    val cardList = remember {
        mutableStateListOf(Card("", ""))
    }


    when (screenState) {
        CreateScreen.TitleScreen -> {
            CreateTitleScreen(
                deckTitle = deckTitle,
                setDeckTitle = setDeckTitle,
                visible = visible,
                setVisibility = setVisibility,
                navigateBack = { navController.popBackStack() },
                toCardScreen = { setScreenState(CreateScreen.CardScreen) }
            )
        }
        CreateScreen.CardScreen -> {
            CreateCardScreen(
                cardList = cardList, //SnapshotStateList<Card>가 전달된다
                setCard = { index, card ->
                    cardList[index] = card // Card field 변경
                },
                addCard = { cardList.add(Card("", "")) }, // 새 Card 추가
                removeCard = { index ->
                    cardList.removeAt(index) // Card 삭제
                    if (cardList.size == 0) cardList.add(Card("", ""))
                    // 삭제된 뒤에 cardList 사이즈가 0인 경우 새 Card 추가
                },
                navigateBack = { navController.popBackStack() },
                onDone = { Log.d("cardList", cardList.joinToString("\n")) }
            )
        }
    }
}

@Composable
fun CreateTitleScreen(
    deckTitle: String,
    setDeckTitle: (String) -> Unit,
    visible: Boolean,
    setVisibility: (Boolean) -> Unit,
    navigateBack: () -> Unit,
    toCardScreen: () -> Unit
) {
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
                IconButton(onClick = navigateBack) {
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
                TextButton(
                    onClick = toCardScreen,
                    enabled = deckTitle.isNotBlank()
                ) {
                    Text(
                        "Next",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
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
            DeckTitleTextField(deckTitle, setDeckTitle)
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

@ExperimentalPagerApi
@Preview
@Composable
fun CreateCardScreenPreview() {
    val cardList = mutableListOf<Card>(
        Card("front", "back"),
        Card("hi", "hello")
    )
    CreateCardScreen(cardList, { index, card -> {} }, {}, {}, navigateBack = { /*TODO*/ }) {

    }
}


@ExperimentalPagerApi
@Composable
fun CreateCardScreen(
    cardList: List<Card>,
    setCard: (index: Int, card: Card) -> Unit,
    addCard: () -> Unit,
    removeCard: (index: Int) -> Unit,
    navigateBack: () -> Unit,
    onDone: () -> Unit
) {

    val pagerState = rememberPagerState() // Pager의 상태 (페이지 수, 현재 페이지 등)

    var prevPageCount by remember { // 이전 페이지 수를 기억
        mutableStateOf(pagerState.pageCount)
    }

    // 스크롤 애니메이션 처리
    LaunchedEffect(key1 = pagerState.pageCount) { // 페이지 수가 변했을 때
        if (prevPageCount < pagerState.pageCount) {
            // 추가된 경우 - 마지막 페이지로 스크롤
            pagerState.animateScrollToPage(pagerState.pageCount - 1, pagerState.currentPageOffset)
        }
        Log.d("pagecount", (pagerState.pageCount - 1).toString())
        prevPageCount = pagerState.pageCount // prevPageCount를 업데이트
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${pagerState.currentPage + 1}/${cardList.size}",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) { // 좌상단 X 버튼
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "close screen"
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                actions = {
                    TextButton(onClick = onDone) { // 우상단 Done 버튼
                        Text(
                            text = "Done",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = addCard, // 카드 추가
                backgroundColor = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .border(width = 2.dp, color = DeepOrange, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add new card",
                    modifier = Modifier.fillMaxSize(.8f)
                )
            }
        }
    ) {
        Column {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                HorizontalPager( // Pager
                    count = cardList.size,
                    state = pagerState, // 선언한 pagerState 사용 (선언하지 않으면 내부에서 자동으로 사용)
                    contentPadding = PaddingValues(start = 32.dp, end = 32.dp)
                    // 양쪽에 이전, 다음 카드를 보여줌
                ) { page ->
                    CardItemField(
                        card = cardList[page], // page에 해당하는 card 전달
                        setCard = { card ->
                            setCard(page, card)
                            // CardItemField가 전달하는 card로 해당 page에 set
                        },
                        removeCard = { removeCard(page) } // page에 해당하는 card 삭제
                    )
                }
            }
        }
    }
}

@Composable
fun CreateScreenTopBar() {

}

// value, setValue 전달
@Composable
fun DeckTitleTextField(deckTitle: String, setDeckTitle: (String) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = deckTitle,
            onValueChange = {
                setDeckTitle(it)
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.h4,
            placeholder = {
                Text(
                    text = "Untitled deck",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.LightGray
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = DeepOrange,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray
            ),
            maxLines = 2,
        )
    }
}
// 여기까지

@Composable
fun CardItemField(
    card: Card,
    setCard: (Card) -> Unit,
    removeCard: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.LightGray),
    ) {
        ConstraintLayout {
            val (front, back, delete, divider) = createRefs()
            TextField(
                value = card.front, //frontText,
                onValueChange = {
                    setCard(Card(it, card.back))// cardList 안의 아이템을 변경

                },
                modifier = Modifier
                    .constrainAs(front) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .padding(8.dp),
                textStyle = MaterialTheme.typography.h6,
                placeholder = {
                    Text(
                        text = "Front",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.LightGray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = DeepOrange,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                maxLines = 2
            )
            Divider(
                modifier = Modifier
                    .constrainAs(divider) {
                        top.linkTo(front.bottom)
                    }
                    .height(2.dp),
                color = Color.LightGray
            )
            TextField(
                value = card.back, //backText,
                onValueChange = {
                    setCard(Card(card.front, it)) // cardList 안의 아이템 변경
                },
                modifier = Modifier
                    .constrainAs(back) {
                        top.linkTo(divider.bottom)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp),
                textStyle = MaterialTheme.typography.body1,
                placeholder = {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.LightGray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = DeepOrange,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            IconButton(
                onClick = removeCard, // card 삭제
                modifier = Modifier.constrainAs(delete) {
                    bottom.linkTo(parent.bottom, 10.dp)
                    start.linkTo(parent.start, 8.dp)
                }
            ) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "delete")
            }
        }
    }
}
