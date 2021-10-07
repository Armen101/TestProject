package com.test.testproject.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.testproject.domain.remote.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.coinstats.app"
private const val CONNECTION_TIMEOUT = 20L
private const val READ_WRITE_TIMEOUT = 20L

val networkModule = module {
    factory { provideMoshi() }

    factory { MoshiConverterFactory.create(get()) }

    factory<Interceptor> { HttpLoggingInterceptor() }

    single { provideApiService(get(), ApiService::class.java) }

    single { provideOkHttpClient(get()) }

    single { provideRetrofit(get(), get()) }
}

private fun provideMoshi() = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun provideOkHttpClient(interceptors: Interceptor) = OkHttpClient().newBuilder().apply {
    addInterceptor(interceptors)
    connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
    readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
}.build()

private fun provideRetrofit(okHttpClient: OkHttpClient, factory: MoshiConverterFactory) =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(factory)
        .build()

private fun <T> provideApiService(retrofit: Retrofit, clazz: Class<T>): T {
    return retrofit.create(clazz)
}
