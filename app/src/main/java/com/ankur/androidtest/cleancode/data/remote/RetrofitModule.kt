package com.ankur.androidtest.cleancode.data.remote

import com.ankur.androidtest.cleancode.data.repository.ContentRepoImpl
import com.ankur.androidtest.cleancode.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideBaseUrl(): String = "https://raw.githubusercontent.com/catchnz/android-test/master/data/"


    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseurl:String): Retrofit = Retrofit.Builder().baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    fun provideHomeApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideRepository(apiService: ApiService): ContentRepository {
        return ContentRepoImpl(apiService)
    }
}