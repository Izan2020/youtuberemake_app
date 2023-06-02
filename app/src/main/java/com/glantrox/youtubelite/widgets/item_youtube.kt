package com.glantrox.youtubelite.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.glantrox.youtubelite.core.models.Item

@Composable
fun ItemVideosYoutube(vid: Item, onTap: () -> Unit) {
    val thumbnailUrl = vid.snippet.thumbnails.medium.url
    val isLiveContent = vid.snippet?.liveBroadcastContent
    val authorPfpUrl = vid.snippet?.thumbnails
    val title = vid.snippet.title
    val authorName = vid.snippet?.channelTitle
    val viewCount = vid.statistics?.viewCount
    val publishedDate = vid.snippet?.publishedAt
    Column(
        modifier = Modifier.clickable { onTap() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Color.Gray)
                .clip(RoundedCornerShape(8.dp)),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                model = thumbnailUrl  , // Thumbnail Image
                contentDescription = ""
            )
            Card(
                modifier = Modifier
                    .align(
                        alignment = Alignment.BottomEnd
                    )
                    .padding(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if(isLiveContent != "none") Color.Red else Color.DarkGray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(if (isLiveContent != "none") "LIVE" else "0:00",
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
        ) {


            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),

                ) {
                IconButton( onClick = {},
                    content = {
                        AsyncImage(
                            model = authorPfpUrl ,
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp)
                        .background(Color.DarkGray)
                        .padding(top = 8.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Text("$authorName • $viewCount views • $publishedDate",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }
        }
    }

}