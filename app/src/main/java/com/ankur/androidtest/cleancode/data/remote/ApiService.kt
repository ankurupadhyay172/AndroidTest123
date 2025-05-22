package com.ankur.androidtest.cleancode.data.remote

import com.ankur.androidtest.cleancode.data.model.ContentModel
import retrofit2.http.GET


interface ApiService {
    @GET("data.json")
    suspend fun getContent():List<ContentModel>
}