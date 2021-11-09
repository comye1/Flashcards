package com.comye1.flashcards.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.comye1.flashcards.SampleDataSet
import com.comye1.flashcards.models.Deck
import java.util.*

class CheggViewModel : ViewModel() {
    // HomeScreen


    // 사용자가 만들거나 북마크한 Deck들
    var myDeckList = mutableStateListOf<Deck>()
        private set

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
        it.deckTitle.lowercase(Locale.getDefault())
            .contains(queryString.value)
    }.toMutableStateList()

    //****************************************

    // 초기화 - 일단 샘플 데이터 활용
    init {
        myDeckList = SampleDataSet.myDeckSample.toMutableStateList()
        totalDeckList = SampleDataSet.totalDeckSample.toMutableStateList()
    }
}
