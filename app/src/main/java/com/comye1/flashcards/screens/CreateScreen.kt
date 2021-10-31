package com.comye1.flashcards.screens

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
//
//    lazy var cardList: List<Card> = listOf()
    val cardList: MutableList<Card> = remember {
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
                cardList,
                { navController.popBackStack() }
            ) {/*onDone*/ }
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
                    enabled = !deckTitle.isNullOrEmpty()
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
    CreateCardScreen(cardList, navigateBack = { /*TODO*/ }) {

    }
}


@ExperimentalPagerApi
@Composable
fun CreateCardScreen(
    cardList: MutableList<Card>,
    navigateBack: () -> Unit,
    onDone: () -> Unit
) {

    // Todo: 카드 추가 로직
    // 뷰모델에 빈 리스트 선언 -> 거기에 추가하는 방식으로.. 하나?

    var pageCount by remember {
        mutableStateOf(10)
    }

    val pagerState = rememberPagerState()

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
                    TextButton(onClick = onDone) {
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
                onClick = {
                    cardList.add(Card("", ""))
                },
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


        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CardItemField()
            HorizontalPager(count = cardList.size, state = pagerState) { page ->
                CardItemField(
                    cardList[page].front,
                    cardList[page].back,
                    { cardList[page].front = it },
                    { cardList[page].back = it },
                    { cardList.removeAt(page)}
                )
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
    frontText: String,
    backText: String,
    setFront: (String) -> Unit,
    setBack: (String) -> Unit,
    deleteCard: () -> Unit
) {

//    //나중에 밖으로 뺄 것
//    val (frontText, setFrontText) = remember {
//        mutableStateOf("")
//    }
//
//    val (backText, setBackText) = remember {
//        mutableStateOf("")
//    }

    Box(
        modifier = Modifier
            .fillMaxWidth(.8f)
            .padding(8.dp)
            .border(2.dp, Color.LightGray),
    ) {
        ConstraintLayout {
            val (front, back, delete, divider) = createRefs()
            TextField(
                value = frontText,
                onValueChange = setFront,
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
                    .fillMaxWidth()
                    .height(2.dp),
                color = Color.LightGray
            )
            TextField(
                value = backText,
                onValueChange = setBack,
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
                onClick = { /*TODO*/ },
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
