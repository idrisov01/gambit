package com.idrisov.gambit

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val BASE_URL = "http://gambit0i.beget.tech/api/"

class App: Application() {

    companion object {

        lateinit var restApi: RestApi
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofit()
        initCol()
    }

    private fun initRetrofit() {

        val json = Json {
            isLenient = true
            encodeDefaults = true
            ignoreUnknownKeys = true
        }

        restApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RestApi::class.java)
    }

    private fun initCol() {

        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .bitmapPoolingEnabled(false)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .build()
            }
            .build()

        Coil.setImageLoader(imageLoader)
    }
}