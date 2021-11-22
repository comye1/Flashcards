package com.comye1.flashcards.repository

import android.util.Log
import com.comye1.flashcards.models.Card
import com.comye1.flashcards.models.Deck
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CheggRepository {

    private val db = Firebase.firestore

    suspend fun getAllDeckList():List<Deck>{

        var deckList = mutableListOf<Deck>()
        db.collection("decks")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    Log.d("chegg", document["deckTitle"].toString())
                    val cardList = document["cardList"] as ArrayList<HashMap<String, String>>
                    var deck = Deck(
                        deckId = document.id,
                        deckTitle = document["deckTitle"] as String,
                        shared = document["shared"] as Boolean,
                        cardList = cardList.map {
                            Card(front = it["front"]!!, back = it["back"]!!)
                        },
                        bookmarked = false
                    )
                    deckList.add(deck)
                }
            }
        return deckList.toList()
    }

    fun getDeck(deckId: String) =
        db.collection("decks")
            .get()
            .result.find { it.id == deckId }

//    fun getUserDeckList(){
//        db.collection("users")
//            .document("devyewon@gmail.com")
//
//            .addOnSuccessListener { result ->
//                Log.d("Chegg", )
//            }
//            .addOnFailureListener { exception ->
//                Log.d("Chegg", "Error getting documents.", exception)
//            }
//    }

//    fun addUserDeck(deck: Deck) {
//        val hashMap = (
//            "created_bookmarked" to true,
//
//        )
//
//        db.collection("decks")
//            .document()
//            .
//
//        db.collection("users")
//            .document("devyewon@gmail.com")
//            .
//
//    }

}