package com.amo.moviekmp.android.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.amo.moviekmp.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit = {},
    onMovieVote: (Int) -> Unit = { _ -> }
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable(enabled = true) {
                onMovieClick(movie)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            ),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            alpha = 0.8f,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .fillMaxSize(),
        )
        Text(
            text = movie.originTitle,
            color = Color.White,
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 4.dp),
        )
        Icon(
            Icons.Filled.Star,
            contentDescription = "",
            tint = if (movie.isVoted) Color.Yellow else Color.White,
            modifier = Modifier
                .padding(4.dp)
                .width(32.dp)
                .height(32.dp)
                .align(Alignment.BottomEnd)
                .background(color = Color.White.copy(alpha = 0f), CircleShape)
                .clickable {
                    onMovieVote(movie.id)
                })
    }
}