package com.example.heartconnect.network

import android.content.Context
import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.heartconnect.errors.ApiExceptions
import com.example.heartconnect.errors.ApiNotRespondingException
import com.example.heartconnect.errors.BadRequestException
import com.example.heartconnect.errors.FetchDataException
import com.example.heartconnect.features.presentation.screens.splash.viewmodel.SplashViewModel
import com.example.heartconnect.local_datastore.LocalDatastore
import com.google.android.gms.common.api.ApiException
import com.google.gson.Gson
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class ApiHandler(private val context: Context) {
    //getting the user token
    private val token = "s"
    private val baseUrl = "/api.github.com"
    private val timeOutDuration = 60L
    private val client: OkHttpClient =
        OkHttpClient.Builder().readTimeout(timeOutDuration, TimeUnit.SECONDS)
            .writeTimeout(timeOutDuration, TimeUnit.SECONDS).build()
    private val gson = Gson()

    // Helper function to create a Retrofit instance
    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
    }

    // GET request
    fun get(api: String, header: Map<String, String>? = null, isAuth: Boolean = false): Any? {
//        val token = if (isAuth) SharedPreference.getToken(context) else null
        val request = Request.Builder().url(baseUrl + api).apply {
            header?.forEach { (key, value) ->
                addHeader(key, value)
            }
            if (isAuth) {
                addHeader("Authorization", "Bearer $token")
            }
            get()
        }.build()
        try {
            val response = client.newCall(request).execute()
            Log.d("API", "$baseUrl$api")
            Log.d("APIStatus", "${response.code}")
            Log.d("APIResponse", "${response.body}")
            return processResponse(response)
        } catch (ex: Exception) {
            throw handleException(ex)
        }
    }

    // Helper function to process the HTTP response
    private fun processResponse(response: okhttp3.Response): Any? {
        val responseBody = response.body.toString()
        return when (response.code) {
            200, 201 -> gson.fromJson(responseBody, Any::class.java) // Successful response
            else -> throw ApiExceptions(response.message, "", "", response.code)
        }
    }

    // Helper function to handle exceptions
    private fun handleException(e: Exception): Exception {
        return when (e) {
            is SocketException -> FetchDataException(e.message)
            is IOException -> BadRequestException(e.message)
            is TimeoutException -> ApiNotRespondingException(e.message)
            else -> e
        }
    }
}