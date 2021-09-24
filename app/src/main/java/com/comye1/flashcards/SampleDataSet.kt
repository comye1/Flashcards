package com.comye1.flashcards

import com.comye1.flashcards.models.Card
import com.comye1.flashcards.models.DECK_ADDED
import com.comye1.flashcards.models.DECK_CREATED
import com.comye1.flashcards.models.Deck

object SampleDataSet {

    val deckSample = listOf(
        Deck(
            deckType = DECK_ADDED,
            deckTitle = "Computer Networks",
            shared = false,
            bookmarked = true,
            cardList = listOf(
                Card("Mesh topology", "A network topology in which every node pair is connected by a point-to-point link"),
                Card("Network topology", "The spatial organization of network devices"),
                Card("Logical topology", "The path messages traverse as they travel between end and central network nodes.")
            )
        ),
        Deck(
            deckType = DECK_ADDED,
            deckTitle = "Information Systems",
            shared = false,
            bookmarked = true,
            cardList = listOf(
                Card("Iterations", "Repeated steps in an SDLC process"),
                Card("System development life cycle", "The process for developing an information system"),
            )
        ),
        Deck(
            deckType = DECK_CREATED,
            deckTitle = "jetpack compose",
            shared = false,
            bookmarked = false,
            cardList = listOf(
                Card("modifier", "Repeated steps in an SDLC process"),
                Card("side effects", "The process for developing an information system"),
                Card("modifier", "Repeated steps in an SDLC process"),
                Card("side effects", "The process for developing an information system"),
            )
        ),
        Deck(
            deckType = DECK_CREATED,
            deckTitle = "Room",
            shared = true,
            bookmarked = false,
            cardList = listOf(
                Card("DAO", "Repeated steps in an SDLC process"),
                Card("suspend", "The process for developing an information system"),
                Card("suspend", "The process for developing an information system"),
                Card("suspend", "The process for developing an information system"),
                Card("suspend", "The process for developing an information system"),
                Card("suspend", "The process for developing an information system"),
            )
        )
    )
}