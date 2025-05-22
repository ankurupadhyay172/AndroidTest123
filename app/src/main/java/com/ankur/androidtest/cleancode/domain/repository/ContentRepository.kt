package com.ankur.androidtest.cleancode.domain.repository

import com.ankur.androidtest.cleancode.domain.model.DomainModel

interface ContentRepository {
    suspend fun getContent():Result<List<DomainModel>>
}