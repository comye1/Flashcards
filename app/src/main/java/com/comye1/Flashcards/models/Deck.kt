package com.comye1.Flashcards.models

data class Deck (
    val deckId: Long = 0L,
    val deckType: Int,
    val deckTitle: String,
    val shared: Boolean,
    val bookmarked: Boolean,
    val cardList: List<Card>
)