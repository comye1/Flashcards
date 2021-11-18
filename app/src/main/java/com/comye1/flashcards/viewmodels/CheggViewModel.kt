package com.comye1.flashcards.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.comye1.flashcards.SampleDataSet
import com.comye1.flashcards.models.DECK_CREATED
import com.comye1.flashcards.models.Deck
import com.comye1.flashcards.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CheggViewModel : ViewModel() {
    // SignIn
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    suspend fun signIn(email: String, displayName: String){
        _user.value = User(email, displayName)
    }

    // HomeScreen

    // 사용자가 만들거나 북마크한 Deck들
    var myDeckList = mutableStateListOf<Deck>()
        private set

    // filterIndex : 새로 로드할 때 초기화 되므로 HomeScreen 내에 둠
    // BUT, 필터링한 결과는 뷰모델에서 반환하자

    // 모든 Deck
    fun getAllDeckList() = myDeckList
    // Bookmark한 Deck
    fun getBookMarkedDeckList() = myDeckList.filter { it.bookmarked }
    // Create한 Deck
    fun getCreatedDeckList() = myDeckList.filter { it.deckType == DECK_CREATED }

    //**************************************

    // SearchScreen

    var searchScreenState = mutableStateOf(SearchState.ButtonScreen)
        private set

    fun toButtonScreen() {
        searchScreenState.value = SearchState.ButtonScreen
    }

    fun toQueryScreen() {
        searchScreenState.value = SearchState.QueryScreen
    }

    fun toResultScreen() {
        searchScreenState.value = SearchState.ResultScreen
    }

    var queryString = mutableStateOf("")
        private set

    fun setQueryString(query: String) {
        queryString.value = query
    }

    // 전체 Deck
    var totalDeckList = mutableStateListOf<Deck>()
        private set

    // Deck 검색 결과 반환
    fun getQueryResult() = totalDeckList.filter {
        it.deckTitle.lowercase()
            .contains(queryString.value.lowercase())
    }.toMutableStateList()

    //****************************************

    // CreateScreen

    var createScreenState = mutableStateOf(CreateScreen.TitleScreen)
        private set

    fun toTitleScreen() {
        createScreenState.value = CreateScreen.TitleScreen
    }

    fun toCardScreen() {
        createScreenState.value = CreateScreen.CardScreen
    }

    // 초기화 - 일단 샘플 데이터 활용
    init {
        myDeckList = SampleDataSet.myDeckSample.toMutableStateList()
        totalDeckList = SampleDataSet.totalDeckSample.toMutableStateList()
    }
}
