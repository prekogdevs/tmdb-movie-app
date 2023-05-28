package com.tmdb.movieapp.di

import com.tmdb.movieapp.AppConstants
import com.tmdb.movieapp.data.remote.MovieApi
import com.tmdb.movieapp.data.repository.MovieRepositoryImpl
import com.tmdb.movieapp.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMovieApi(okHttpClient: OkHttpClient): MovieApi =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi) =
        MovieRepositoryImpl(api = api) as MovieRepository
}