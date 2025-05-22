package com.ankur.androidtest.cleancode.domain.usecases

import com.ankur.androidtest.cleancode.domain.repository.ContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContentUseCase @Inject constructor(val repository: ContentRepository) {
    operator fun invoke() = flow {
        val response = repository.getContent()
        emit(response)
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}
