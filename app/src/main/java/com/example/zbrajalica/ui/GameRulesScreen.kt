package com.example.zbrajalica.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zbrajalica.R
import com.example.zbrajalica.ZbrajalicaApp
import com.example.zbrajalica.ui.theme.ZbrajalicaTheme

@Composable
fun GameRulesScreen(navigateToHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(25.dp)) // White background with rounded corners
                .padding(25.dp) // Padding inside the box
        ) {
            Text(
                text =  stringResource(id = R.string.game_rules_text),
                textAlign = TextAlign.Justify,
                lineHeight = 30.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp)) // Add spacing between the text and the button
        IconButton(onClick = { navigateToHome() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back arrow",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GemeRulesPreview() {
    GameRulesScreen({})
}