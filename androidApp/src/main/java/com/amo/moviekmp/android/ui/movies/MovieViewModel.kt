package com.amo.moviekmp.android.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.amo.moviekmp.model.Movie
import com.amo.moviekmp.network.DefaultTmdbRemoteDataSource
import com.amo.moviekmp.network.TmdbRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(
    private val remoteDataSource: TmdbRemoteDataSource
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            val response = remoteDataSource.getMovies()
            _movies.update { response.results }
            throw UnsupportedOperationException("Throw to test Github Bisect")
        }
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return MovieViewModel(DefaultTmdbRemoteDataSource()) as T
            }
        }
    }

}