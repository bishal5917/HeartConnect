package com.example.heartconnect.features.domain.usecases

interface Usecase<Type, Params> {
    suspend fun call(params: Params): Type
}