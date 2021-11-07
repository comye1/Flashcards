package com.comye1.flashcards.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.comye1.flashcards.SampleDataSet
import com.comye1.flashcards.models.Deck
import java.util.*

class CheggViewModel : ViewModel() {

    // 사용자가 만들거나 북마크한 Deck들
    var myDeckList = mutableStateListOf<Deck>()
        private set

    // 전체 Deck
    var totalDeckList = mutableStateListOf<Deck>()
        private set

    // Deck 검색 결과 반환
    fun getQueryResult(queryString: String) = totalDeckList.filter {
        it.deckTitle.lowercase(Locale.getDefault())
            .contains(queryString)
    }.toMutableStateList()

    // 초기화 - 일단 샘플 데이터 활용
    init {
        myDeckList = SampleDataSet.myDeckSample.toMutableStateList()
        totalDeckList = SampleDataSet.totalDeckSample.toMutableStateList()
    }
}