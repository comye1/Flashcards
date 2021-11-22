package com.comye1.flashcards.models

data class Deck(
    val deckId: String = "",
    val deckType: Int = DECK_ADDED,
    val deckTitle: String,
    val shared: Boolean,
    val bookmarked: Boolean,
    val cardList: List<Card> = listOf()
)

/*
Home화면에서..

 */

const val DECK_CREATED = 0
const val DECK_ADDED = 1