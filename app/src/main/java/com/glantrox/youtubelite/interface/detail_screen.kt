package com.glantrox.youtubelite.`interface`


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import coil.compose.AsyncImage
import com.glantrox.youtubelite.R
import com.glantrox.youtubelite.constants.PlayingVideoState
import com.glantrox.youtubelite.core.models.Item
import com.glantrox.youtubelite.providers.YoutubeViewModel
import com.glantrox.youtubelite.route.AppNavigator
import com.glantrox.youtubelite.ui.theme.YoutubeLiteTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@SuppressLint("PrivateResource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenUI(
    viewModel: YoutubeViewModel = YoutubeViewModel(),
    navController: NavHostController = rememberNavController()) {


    var isExpanded by remember { mutableStateOf(false) }
    val iconDown: Int = com.google.android.material.R.drawable.material_ic_menu_arrow_down_black_24dp
    val iconUp: Int = com.google.android.material.R.drawable.material_ic_menu_arrow_up_black_24dp


    val _currentVideo: Item? = viewModel.playingVideo.value.currentVideo
    val _currentState: PlayingVideoState = viewModel.currentVideoState.value


    Scaffold() {
        when(_currentState) {
            PlayingVideoState.loading -> Column(
                modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                CircularProgressIndicator()
                Text("Loading")
            }

            PlayingVideoState.error -> Column(
                modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text("Error")
            }

            PlayingVideoState.success -> Column {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(it)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            alignment = Alignment.Center
                        )
                    )

                    YoutubeScreen(videoId = _currentVideo?.id, modifier = Modifier.fillMaxSize())

                    IconButton(onClick = { AppNavigator().pop(navController)
                    },
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = "backIcon",
                                tint = Color.White
                            )
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                    ) {
                        Text(
                            _currentVideo?.snippet?.title.toString(),
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.width(280.dp)
                        )
                        Text("${_currentVideo?.statistics?.viewCount} views • ${_currentVideo?.snippet?.publishedAt}",
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    }

                    IconButton(onClick = { isExpanded = !isExpanded },
                        content = {
                            Icon(
                                painter = painterResource(if(isExpanded) iconDown else iconUp),
                                contentDescription ="",
                                modifier = Modifier.align(
                                    alignment = Alignment.Top
                                )
                            )
                        },
                        modifier = Modifier.align(
                            alignment = Alignment.Top
                        )
                    )
                }
                AnimatedVisibility(isExpanded) {
                    Column {
                        Text(
                            _currentVideo?.snippet?.description ?: "",
                            style = TextStyle(
                                fontSize = 12.sp
                            ),
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 6.dp
                                )
                                .fillMaxWidth()
                        )
                        Divider(
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(62.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.size(12.dp))
                        IconButton( onClick = {},
                            content = {
                                AsyncImage(
                                    model = "" ,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            },
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(42.dp)
                                .background(Color.DarkGray)

                        )
                        Spacer(modifier = Modifier.size(12.dp))

                        Column {
                            _currentVideo?.snippet?.let { it1 ->
                                Text(
                                    it1.channelTitle,
                                    style = TextStyle(
                                        fontWeight   = FontWeight.Bold
                                    )
                                )
                            }
                            Text("0 subscribers",
                                style = TextStyle(
                                    fontWeight = FontWeight.Light,
                                    fontSize = 14.sp
                                )
                            )
                        }

                    }
                }
            }

            else -> {}
        }
    }
}

@Composable
fun YoutubeScreen(
    videoId: String?,
    modifier: Modifier
) {
    val ctx = LocalContext.current
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId!!, 0f)
                }
            }
        )
        view
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewDetail() {
    YoutubeLiteTheme {
        DetailScreenUI()
    }
}