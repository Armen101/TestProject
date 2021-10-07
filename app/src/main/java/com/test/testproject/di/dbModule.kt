package com.test.testproject.di

import com.test.testproject.domain.local.AppDatabase
import com.test.testproject.domain.local.CoinToEntityConverter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    single { AppDatabase.buildDatabase(androidContext()) }

    factory { get<AppDatabase>().coinDao() }

    factory { CoinToEntityConverter() }

}