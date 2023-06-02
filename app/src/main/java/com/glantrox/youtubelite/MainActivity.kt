package com.glantrox.youtubelite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.glantrox.youtubelite.providers.YoutubeViewModel
import com.glantrox.youtubelite.route.AppNavigator
import com.glantrox.youtubelite.ui.theme.YoutubeLiteTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: YoutubeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[YoutubeViewModel::class.java]

        setContent {
            YoutubeLiteTheme {
            AppNavigator().navigations(viewModel)
            }
        }
    }
}

