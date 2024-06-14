package com.example.zbrajalica.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel:ViewModel() { //viewmodel outlives the lifecyc of the avtivity

    private val _uiState = MutableStateFlow(GameUiState())
    //read only access
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userAnswer by mutableStateOf("")
        private set //ui can read this prop, but not set it directly


    private var stopwatchJob: Job? = null
    private var stopwatchRunning = false

    init {
        restartGame()
        startStopwatch()
    }

    fun updateUserAnswer(answer: String){
        userAnswer = answer
    }

    private fun setNumbers() {
        val randomOperation = if (Random.nextBoolean()) '+' else '-'
        val firstNr = Random.nextInt(1, 10)
        val secondNr = Random.nextInt(1, 10)
        _uiState.update { currentState ->
            currentState.copy(
                firstNr = maxOf(firstNr, secondNr),
                secondNr = minOf(firstNr, secondNr),
                operation = randomOperation
            )
        }
    }

    private fun checkUserAnswer(): Boolean{
        val correctAnswer = if (_uiState.value.operation == '+') _uiState.value.firstNr + _uiState.value.secondNr else _uiState.value.firstNr - _uiState.value.secondNr
        return (userAnswer == (correctAnswer).toString())
    }

    fun onSubmitAnswer() {

        if(!_uiState.value.answered){
            var answerStatus = ""
            var score = _uiState.value.score
            var answered = false
            if(checkUserAnswer()){
                answerStatus = "1"
                score +=1
                answered= true
                pauseStopwatch()

            }else answerStatus= "0"

            _uiState.update { currentState ->
                currentState.copy(
                    answerStatus = answerStatus,
                    score =  score,
                    answered = answered
                )
            }
        }
    }

    fun onPlayAgain(){
        setNumbers()
        continueStopwatch()
        userAnswer= ""
        _uiState.update { currentState ->
            currentState.copy(
                answerStatus = "",
                answered = false,
                rounds = currentState.rounds.inc()

            )
        }
    }

    fun restartGame(){
        _uiState.value = GameUiState()
        setNumbers()
        resetStopwatch()
        startStopwatch()
    }

    private fun startStopwatch() {
        if (stopwatchRunning) return
        stopwatchRunning = true
        stopwatchJob = viewModelScope.launch { //launches a new coroutine (non blocking)
            while (stopwatchRunning) {
                delay(1000L)
                _uiState.update { currentState ->
                    currentState.copy(currentTime = currentState.currentTime + 1)
                }
            }
        }
    }

    private fun pauseStopwatch() {
        stopwatchRunning = false
        stopwatchJob?.cancel()
    }

    private fun continueStopwatch() {
        if (!stopwatchRunning) {
            startStopwatch()
        }
    }

    private fun resetStopwatch() {
        pauseStopwatch()
        _uiState.update { currentState ->
            currentState.copy(currentTime = 0)
        }
    }



}