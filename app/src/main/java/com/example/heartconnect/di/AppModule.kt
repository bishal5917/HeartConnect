package com.example.heartconnect.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.ViewModelProvider
import com.example.heartconnect.features.data.datasources.UserRemoteDatasource
import com.example.heartconnect.features.data.datasources.UserRemoteDatasourceImpl
import com.example.heartconnect.features.data.repositories.UserRepositoryImpl
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.features.domain.usecases.GetConversationsUsecase
import com.example.heartconnect.features.domain.usecases.GetHomeUsersUsecase
import com.example.heartconnect.features.presentation.screens.home.viewmodel.HomeViewModel
import com.example.heartconnect.features.presentation.screens.login.LoginViewModel
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.local_datastore.LocalDatastore
import com.example.heartconnect.local_datastore.LocalDatastoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }),
            produceFile = { context.preferencesDataStoreFile("datastore") })
    }

    @Provides
    fun providesDataStore(datastore: DataStore<Preferences>): LocalDatastore =
        LocalDatastoreImpl(datastore)

    @Provides
    fun provideDataSource(): UserRemoteDatasource {
        return UserRemoteDatasourceImpl()
    }

    @Provides
    fun provideRepository(dataSource: UserRemoteDatasource): UserRepository {
        return UserRepositoryImpl(dataSource)
    }

    //registering usecases
    @Provides
    fun provideHomeUsecase(repo: UserRepository): GetHomeUsersUsecase {
        return GetHomeUsersUsecase(repo)
    }
    @Provides
    fun provideChatUsecase(repo: UserRepository): GetConversationsUsecase {
        return GetConversationsUsecase(repo)
    }


}