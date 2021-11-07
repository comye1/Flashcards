package com.comye1.flashcards.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.comye1.flashcards.models.Deck
import com.comye1.flashcards.repository.CheggRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheggViewModel @Inject constructor(
    repository: CheggRepository
):ViewModel() {

    var myDeckList = mutableStateListOf<Deck>()
        private set

    init {
        myDeckList = repository.getUserCheggList()
    }
}