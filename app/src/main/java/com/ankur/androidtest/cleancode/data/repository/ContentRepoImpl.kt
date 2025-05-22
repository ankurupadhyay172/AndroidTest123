package com.ankur.androidtest.cleancode.data.repository

import com.ankur.androidtest.cleancode.data.remote.ApiService
import com.ankur.androidtest.cleancode.domain.model.DomainModel
import com.ankur.androidtest.cleancode.domain.repository.ContentRepository
import javax.inject.Inject


class ContentRepoImpl @Inject constructor(val apiService: ApiService): ContentRepository {
    override suspend fun getContent(): Result<List<DomainModel>> {
        return try {
            val response = apiService.getContent()
            val listOfDomainModel = response.map { DomainModel(
                id = it.id,
                content = it.content,
                title = it.title,
                subtitle = it.subtitle
            ) }
            Result.success(listOfDomainModel)
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}