package com.comye1.flashcards.models

data class Deck(
//    val deckId: Long = 0L,
    val deckType: Int,
    val deckTitle: String,
    val shared: Boolean,
    val bookmarked: Boolean,
    val cardList: List<Card>
)

/*
Home화면에서..

 */

const val DECK_CREATED = 0
const val DECK_ADDED = 1