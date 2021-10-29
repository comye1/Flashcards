package com.comye1.flashcards.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.comye1.flashcards.ui.theme.DeepOrange

enum class SearchState {
    ButtonScreen,
    QueryScreen,
    ResultScreen
}

// 전체
@Composable
fun SearchScreen(navController: NavHostController) {

    val (screenState, setScreenState)= remember {
        mutableStateOf(SearchState.ButtonScreen)
    }

    var (queryString, setQueryString) = remember {
        mutableStateOf("")
    }

    when (screenState) {
        SearchState.ButtonScreen -> {
            SearchButtonScreen {
                if (queryString.isNotBlank()) {
                    setScreenState(SearchState.ResultScreen)
                } else {
                    setScreenState(SearchState.QueryScreen)
                }
            }
        }
        SearchState.QueryScreen -> {
            SearchQueryScreen(
                queryString,
                setQueryString,
                { setScreenState(SearchState.ButtonScreen) },
                { setScreenState(SearchState.ResultScreen) }
            )
        }
        SearchState.ResultScreen -> {
            SearchResultScreen(
                queryString,
                setQueryString,
                { setScreenState(SearchState.ButtonScreen) },
                {}
            )
        }
    }


}

@Composable
fun SearchResultScreen(
    queryString: String,
    setQueryString: (String) -> Unit,
    toButtonScreen: () -> Unit,
    function1: () -> Unit
) {
    Scaffold(
        topBar = {
            SearchTopBar(
                queryString = queryString,
                setQueryString = setQueryString,
                onBackButtonClick = toButtonScreen,
                onSearchKey = { } //TODO : onSearchKey
            )
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.25f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "검색결과",
                style = MaterialTheme.typography.body1,
                fontSize = 20.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SearchButtonScreen(onButtonClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Find flashcards",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 16.dp) //column에서는 왼,오른쪽이 vertical
        ) {
            FindFlashCards(onClick = onButtonClick)
            Spacer(modifier = Modifier.height(24.dp))
            Divider(
                Modifier
                    .fillMaxWidth(.15f)
                    .height(4.dp), color = DeepOrange
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Choose your subject",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Jump into studying with free flashcards that are right for you",
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(16.dp))
            repeat(7) {
                SubjectItem()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// onClick 추가, text 수정
@Composable
fun FindFlashCards(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
            .clip(shape = CircleShape)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search flashcards",
            tint = Color.Gray
        )
        Text(text = " Find flashcards", style = MaterialTheme.typography.body1, color = Color.Gray)
    }
}
// 여기까지

@Composable
fun SubjectItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                shape = RoundedCornerShape(size = 8.dp),
                width = 2.dp,
                color = Color.LightGray
            )
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .clickable { // 추가됨

            }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Computer,
            contentDescription = "bookmark",
            tint = DeepOrange,
            modifier = Modifier.size(36.dp)
        )
        Text(
            text = "  Computer Science",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}
//
//@Preview
//@Composable
//fun SearchQueryScreenPreview(){
//    SearchQueryScreen(queryString = "", setQueryString = {}) {
//
//    }
//}

@Composable
fun SearchQueryScreen(
    queryString: String,
    setQueryString: (String) -> Unit,
    toButtonScreen: () -> Unit,
    toResultScreen: () -> Unit
) {

    Scaffold(
        topBar = {
            SearchTopBar(
                queryString = queryString,
                setQueryString = setQueryString,
                onBackButtonClick = toButtonScreen,
                onSearchKey = toResultScreen
            )
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.25f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "what are you learning today?",
                style = MaterialTheme.typography.body1,
                fontSize = 20.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SearchTopBar(
    queryString: String,
    setQueryString: (String) -> Unit,
    onBackButtonClick: () -> Unit,
    onSearchKey: () -> Unit
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "navigate back"
                )
            }
        },
        title = {
            TextField(
                value = queryString,
                onValueChange = setQueryString,
                placeholder = {
                    Text(
                        text = "Find flashcards",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = DeepOrange,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchKey()
                    }
                ),
            )
        },
        actions = {
            if (queryString.isNotBlank()) {
                IconButton(onClick = { setQueryString("") }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "delete")
                }
            }
        }
    )
}