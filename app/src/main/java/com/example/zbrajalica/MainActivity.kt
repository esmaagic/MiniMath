package com.example.zbrajalica

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import com.example.zbrajalica.ui.theme.ZbrajalicaTheme
import com.example.zbrajalica.ui.utils.LanguagePreference
import com.example.zbrajalica.ui.utils.setLocale
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val language = LanguagePreference.getLanguage(this)
        if (language != null) {
            setLocale(this, language)
        }
        setContent {
            ZbrajalicaTheme {

                val windowSize = calculateWindowSizeClass(this)
                Surface (
                ){
                    ZbrajalicaApp(windowSize = windowSize.heightSizeClass) { locale ->
                        LanguagePreference.setLanguage(this, locale.language)
                        setLocale(this, locale.language)
                        recreate() // Restart activity to apply changes
                    }
                }



            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun MainPreview() {
    ZbrajalicaTheme {

    }
}