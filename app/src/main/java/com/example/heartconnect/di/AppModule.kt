package com.example.heartconnect.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.heartconnect.features.data.datasources.UserRemoteDatasource
import com.example.heartconnect.features.data.datasources.UserRemoteDatasourceImpl
import com.example.heartconnect.features.data.repositories.UserRepositoryImpl
import com.example.heartconnect.features.domain.repositories.UserRepository
import com.example.heartconnect.features.domain.usecases.*
import com.example.heartconnect.services.local.LocalDatastore
import com.example.heartconnect.services.local.LocalDatastoreImpl
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

    @Provides
    fun provideMessagesUsecase(repo: UserRepository): GetMessagesUsecase {
        return GetMessagesUsecase(repo)
    }

    @Provides
    fun provideSendMessageUsecase(repo: UserRepository): SendMessageUsecase {
        return SendMessageUsecase(repo)
    }

    @Provides
    fun provideCreateChatUsecase(repo: UserRepository): CreateChatUsecase {
        return CreateChatUsecase(repo)
    }

    @Provides
    fun provideRegisterUserUsecase(repo: UserRepository): RegisterUserUsecase {
        return RegisterUserUsecase(repo)
    }
}