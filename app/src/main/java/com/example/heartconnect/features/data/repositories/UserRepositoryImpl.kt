package com.example.heartconnect.features.data.repositories

import com.example.heartconnect.features.data.datasources.UserRemoteDatasource
import com.example.heartconnect.features.data.models.HomeUser
import com.example.heartconnect.features.domain.repositories.UserRepository
import okhttp3.internal.wait
import javax.inject.Inject

class UserRepositoryImpl(private val userDataSource: UserRemoteDatasource) :
    UserRepository {
    override suspend fun getHomeUsers(id: String): List<HomeUser> {
        try {
            return userDataSource.getHomeUsers(id)
        } catch (ex: Exception) {
            throw ex
        }
    }
}