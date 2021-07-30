package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    init {
        Log.i("ATM", "Inside init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ATM", "Inside on cleared")
    }
}