package com.example.zbrajalica.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zbrajalica.R
import com.example.zbrajalica.ui.theme.MintGreen
import com.example.zbrajalica.ui.theme.SoftRed


@Composable
fun GameScreen(
    windowSize: WindowHeightSizeClass,
    navigateToHome: () -> Unit,
    viewModel: GameViewModel = viewModel(),
    ){

   val uiState by viewModel.uiState.collectAsState()


   when (windowSize) {
       WindowHeightSizeClass.Compact -> {
           Row(
               modifier = Modifier.fillMaxWidth()
           ) {
               // First column
               Box(
                   modifier = Modifier
                       .weight(1f)
                       .padding(8.dp)
                       .fillMaxHeight()
               ) {
                   Column {
                       GameData(
                           round = uiState.rounds,
                           time =uiState.currentTime ,
                           score = uiState.score,navigateToHome,)
                       GameButtons(onPlayAgain = { viewModel.onPlayAgain() }, restartGame = {viewModel.restartGame()})
                   }

               }

               // Second column
               Box(
                   modifier = Modifier
                       .weight(1f)
                       .padding(8.dp)
                       .fillMaxHeight()
               ) {
                   GameLayout(uiState = uiState)
               }
           }
       }
       else->{
           Column {
               GameData(
                   round = uiState.rounds,
                   time =uiState.currentTime ,
                   score = uiState.score,navigateToHome,)
               GameLayout(uiState = uiState)
               GameButtons(onPlayAgain = { viewModel.onPlayAgain() }, restartGame = {viewModel.restartGame()})
           }
       }
       }










}


    















@Composable
fun GameLayout(uiState: GameUiState, viewModel: GameViewModel = viewModel()){

        Column(modifier = Modifier
            .padding(20.dp),)
        {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 0.dp
                    ),
                )
                .border(width = 1.dp, color = Color.Gray),
                contentAlignment = Alignment.Center
            ){
                Text(text ="${uiState.firstNr} ${uiState.operation} ${uiState.secondNr}", fontSize = 60.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

           Surface(modifier = Modifier.padding(16.dp)) {
               OutlinedTextField(
                   value = viewModel.userAnswer,
                   onValueChange = { viewModel.updateUserAnswer(it) },
                   modifier = Modifier
                       .fillMaxWidth()
                       .background(Color.White)
                       .padding(top = 0.dp),
                   label = { Text(stringResource(id = R.string.answer)) } ,
                   singleLine = true,
                   shape = shapes.large,
                   keyboardOptions = KeyboardOptions.Default.copy(
                       keyboardType = KeyboardType.Number,
                   ),
                   keyboardActions = KeyboardActions(
                       onDone = { viewModel.onSubmitAnswer()
                           viewModel.updateUserAnswer("")},
                   )
               )
           }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Text(
                    text = when (uiState.answerStatus) {
                        "1" -> stringResource(id = R.string.correct_answer)
                        "" -> ""
                        else -> stringResource(id = R.string.incorrect_answer)
                    },
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center, // Center the text horizontally and vertically
                    color = when (uiState.answerStatus) {
                        "1" -> MintGreen // Change color based on answerStatus
                        else -> Color(0xFFAD1313) // Change color based on answerStatus
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))



        }
}

@Composable
fun GameButtons(onPlayAgain: ()->Unit, restartGame: ()->Unit){
    Column(
        modifier = Modifier.padding(32.dp)
    ) {



        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 10.dp,bottomStart=10.dp ,bottomEnd = 0.dp),
            // Padding to create gap between buttons
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            onClick = onPlayAgain
        ) {
            Text(fontSize = 18.sp, // Set the font size
                fontWeight = FontWeight.Bold,text = stringResource(id = R.string.play_again))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 10.dp,bottomStart=10.dp ,bottomEnd = 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SoftRed),
            onClick = restartGame
        ) {
            Text(fontSize = 18.sp, // Set the font size
                fontWeight = FontWeight.Bold,text = stringResource(id = R.string.restart))
        }

    }
}

@Composable
fun GameData(
    round: Int,
    time: Int,
    score: Int,
    navigateToHome: ()->Unit,

){
    Column (modifier = Modifier
        .wrapContentHeight()
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            IconButton(onClick = {navigateToHome()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back arrow",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(0.dp) // Set the size of the icon
                )
            }
            val context = LocalContext.current
            IconButton(onClick = { shareOrder(rounds = round, score = score, currentTime = time, context = context) }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Back arrow",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(0.dp) // Set the size of the icon
                )
            }


            Text(text = stringResource( R.string.round, round),fontWeight = FontWeight.Bold)

        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = stringResource(id = R.string.timer, time),fontWeight = FontWeight.Bold)
            Text(text = stringResource(R.string.score, score),fontWeight = FontWeight.Bold)
        }
    }
}


private fun shareOrder(context: Context, rounds: Int, currentTime:Int, score: Int) {
    val subject = context.getString(R.string.new_game_stats)

    val summary = buildString {
        append("Rounds: ${rounds}\n")
        append("Time: ${currentTime} seconds\n")
        append("Score: ${score}\n")
        // Add more stats as needed
    }

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_game_stats)
        )
    )
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameScreen(navigateToHome = { /*TODO*/ }, windowSize = WindowHeightSizeClass.Compact)

}