package com.example.zbrajalica.ui

data class GameUiState(

    val firstNr: Int = 0,
    val secondNr: Int = 0,
    val operation: Char = '+',
    val currentTime: Int = 0,
    val totalTime: Int = 0,
    val userAnswer: Int = 0,
    val rounds: Int = 1,
    val score: Int = 0,
    val answered: Boolean = false,
    val answerStatus: String = ""
)
