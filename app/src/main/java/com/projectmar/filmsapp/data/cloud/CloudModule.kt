package com.projectmar.filmsapp.data.cloud

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CloudModule {
    fun <T> service(clasz: Class<T>): T

    abstract class Abstract : CloudModule {
        protected abstract val interceptorLevel: HttpLoggingInterceptor.Level
        protected open val baseUrl: String = "https://kinopoiskapiunofficial.tech/"

        override fun <T> service(clasz: Class<T>): T {
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(interceptorLevel)
            }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(clasz)
        }
    }

    class Debug() : Abstract() {
        override val interceptorLevel = HttpLoggingInterceptor.Level.BODY
    }

    class Release() : Abstract() {
        override val interceptorLevel = HttpLoggingInterceptor.Level.NONE
    }
}