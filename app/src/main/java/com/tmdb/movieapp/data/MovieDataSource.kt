package com.tmdb.movieapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.movieapp.data.remote.mapper.toSimplifiedMovie
import com.tmdb.movieapp.domain.SimplifiedMovie
import com.tmdb.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val movieRepository: MovieRepository,
    private val query: String
) : PagingSource<Int, SimplifiedMovie>() {
    override fun getRefreshKey(state: PagingState<Int, SimplifiedMovie>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimplifiedMovie> {
        return try {
            val page = params.key ?: 1
            val previousKey = if (page == 1) null else page - 1

            val response = movieRepository.getMovies(query = query, page = page)
            val results = response.results
            val movies = mutableListOf<SimplifiedMovie>()
            results.forEach {movieSearchResult ->
                val movieWithDetails = movieRepository.getMovieById(movieId = movieSearchResult.id)
                val movieWithBudget = movieWithDetails.toSimplifiedMovie()
                movies.add(movieWithBudget)
            }
            LoadResult.Page(
                data = movies.toList(),
                prevKey = previousKey,
                nextKey = if (response.results.isNotEmpty()) response.page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}