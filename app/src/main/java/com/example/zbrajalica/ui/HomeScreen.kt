package com.example.zbrajalica.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zbrajalica.R
import java.util.Locale

@Composable
fun HomeScreen(onPlayButtonClicked: ()->Unit, onRulesButtonClicked: ()->Unit){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 32.sp,  // Increase the text size
            fontWeight = FontWeight.Bold,  // Make the text bold
            modifier = Modifier // Add some space below the text
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            onClick = { onPlayButtonClicked() },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp,bottomStart=5.dp ,bottomEnd = 5.dp),
        ) {
            Text(text = stringResource(id = R.string.start_game),fontSize = 25.sp, // Set the font size
                fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onRulesButtonClicked() },
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp,bottomStart=5.dp ,bottomEnd = 5.dp),
            ) {
            Text(text = stringResource(id = R.string.game_rules),fontSize = 25.sp, // Set the font size
                fontWeight = FontWeight.Bold)
        }


    }
}

