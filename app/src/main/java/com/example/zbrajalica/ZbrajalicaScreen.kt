package com.example.zbrajalica

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zbrajalica.ui.GameViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zbrajalica.ui.GameRulesScreen
import com.example.zbrajalica.ui.GameScreen
import com.example.zbrajalica.ui.HomeScreen


import com.example.zbrajalica.ui.theme.ZbrajalicaTheme
import java.util.Locale


enum class ZbrajalicaScreen(){
    Home,
    Game,
    Rules
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZbrajalicaAppBar(
    modifier: Modifier = Modifier,
    onLanguageSelected: (Locale) -> Unit
) {
    TopAppBar(
        title = {
                Text(text = stringResource(id = R.string.app_name), color = Color.White)
           },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        actions = {

            Row(modifier = Modifier.padding(16.dp)) {
                val currentLocale = LocalContext.current.resources.configuration.locale
                val isEnglishSelected = currentLocale.language == "en"

                Text(
                    text = "Eng",
                    style = if (isEnglishSelected) TextStyle(fontWeight = FontWeight.Bold) else TextStyle.Default,
                    modifier = Modifier.clickable { onLanguageSelected(Locale.ENGLISH) }, color = Color.White
                )

                Text(
                    text = "/",
                    style = TextStyle.Default,
                    modifier = Modifier.padding(horizontal = 8.dp), color = Color.White
                )

                Text(
                    text = "Bos",
                    style = if (!isEnglishSelected) TextStyle(fontWeight = FontWeight.Bold) else TextStyle.Default,
                    color = Color.White,
                    modifier = Modifier.clickable { onLanguageSelected(Locale("bs")) }
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Blue)

    )
}



@Composable
fun ZbrajalicaApp(
    windowSize: WindowHeightSizeClass,
    navController: NavHostController = rememberNavController(), //object handles navigation, can call navigate(somewhere)
    onLanguageSelected: (Locale) -> Unit
){

    Scaffold ( //composable enables persistent ui
        topBar = { ZbrajalicaAppBar(onLanguageSelected = onLanguageSelected) }
    ){
        innerPadding ->
        NavHost( // components to navigate screens
            navController = navController,
            startDestination = ZbrajalicaScreen.Home.name,
            modifier = Modifier.padding(innerPadding)){

            composable(route = ZbrajalicaScreen.Game.name){
                GameScreen(
                    windowSize = windowSize,
                    navigateToHome = {
                        navController.navigate(ZbrajalicaScreen.Home.name)
                    })
            }

            composable(route = ZbrajalicaScreen.Home.name){
                HomeScreen(
                    onPlayButtonClicked = {
                        navController.navigate(ZbrajalicaScreen.Game.name)
                    },
                    onRulesButtonClicked = {
                        navController.navigate(ZbrajalicaScreen.Rules.name)
                    })
            }

            composable(route = ZbrajalicaScreen.Rules.name){
                GameRulesScreen(
                    navigateToHome = {
                        navController.navigate(ZbrajalicaScreen.Home.name)
                    })
            }
        }
    }
}
















