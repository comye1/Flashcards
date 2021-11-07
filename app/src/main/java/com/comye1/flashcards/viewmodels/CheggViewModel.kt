package com.comye1.flashcards.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.comye1.flashcards.SampleDataSet
import com.comye1.flashcards.models.Deck

class CheggViewModel():ViewModel() {

    var myDeckList = mutableStateListOf<Deck>()
        private set

    init {
        myDeckList = SampleDataSet.deckSample.toMutableStateList()
    }
}