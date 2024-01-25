package com.amo.moviekmp.android.ui.movies

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amo.moviekmp.model.Movie

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel
) {
    val movies: List<Movie> by viewModel.movies.collectAsState()
    MovieList(
        modifier,
        movies
    )
}


@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: List<Movie> = emptyList()
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(100.dp),
        content = {
            items(
                movies,
                key = { model -> model.id },
            ) { movie ->
                MovieCard(movie = movie)
            }
        })


}