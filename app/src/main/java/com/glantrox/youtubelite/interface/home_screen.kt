package com.glantrox.youtubelite.`interface`

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.glantrox.youtubelite.R
import com.glantrox.youtubelite.constants.YoutubeState

import com.glantrox.youtubelite.providers.YoutubeViewModel
import com.glantrox.youtubelite.route.AppNavigator
import com.glantrox.youtubelite.route.Screens
import com.glantrox.youtubelite.ui.theme.YoutubeLiteTheme
import com.glantrox.youtubelite.widgets.ItemVideosYoutube

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(viewModel: YoutubeViewModel = YoutubeViewModel(), nav: NavHostController = rememberNavController()) {
    viewModel.getListOfVideos()
    val listOfVideos= viewModel.listOfVideos.value
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(onClick = {
                        AppNavigator().pop(nav)
                    },
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_more_vert_24),
                                contentDescription = "backIcon",
                            )
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ },
                        content = {
                            Image(
                                painter = painterResource(R.drawable.drawable_yt),
                                contentDescription = "backIcon"
                            )
                        }
                    )
                },
                title = {
                    Text(
                        "YouTube",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    ) {
        when(viewModel.currentListState.value) {
            YoutubeState.loading -> Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text("Loading")
            }

            YoutubeState.success -> LazyColumn(
                modifier = Modifier.padding(it),
            ) {
                itemsIndexed(listOfVideos.videos) { _, item ->
                    ItemVideosYoutube(item, onTap = {
                        viewModel.getVideoDetail(item.id)
                        AppNavigator().push(nav, Screens.detailScreen.route)
                    })
                }

            }
            YoutubeState.error -> Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text("Error Loading Data")
            }
            else -> {}
        }
    }


}
@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    YoutubeLiteTheme {
        HomeScreenUI()
    }
}