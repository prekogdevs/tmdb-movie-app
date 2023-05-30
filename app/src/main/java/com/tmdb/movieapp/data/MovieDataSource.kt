package com.tmdb.movieapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.movieapp.data.remote.response.MovieSearchResult
import com.tmdb.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val movieRepository: MovieRepository,
    private val query: String,
    private val initialPage: Int? = null
) : PagingSource<Int, MovieSearchResult>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSearchResult>): Int? {
        return initialPage
            ?: state.anchorPosition?.let { position ->
                val page = state.closestPageToPosition(position)
                page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
            }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearchResult> {
        return try {
            val pagePosition = params.key ?: 1
            val response = movieRepository.getMovies(query = query, page = pagePosition)
            val previousKey = if (pagePosition == 1) null else pagePosition - 1
            val nextKey = if (response.results.isNotEmpty()) response.page + 1 else null
            LoadResult.Page(
                data = response.results,
                prevKey = previousKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}