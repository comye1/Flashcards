package com.comye1.flashcards.repository

import androidx.compose.runtime.toMutableStateList
import com.comye1.flashcards.SampleDataSet

class CheggRepository {

    fun getUserCheggList() = SampleDataSet.myDeckSample.toMutableStateList()

}