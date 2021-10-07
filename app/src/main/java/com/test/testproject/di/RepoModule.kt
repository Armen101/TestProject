package com.test.testproject.di

import com.test.testproject.repo.CryptoRepo
import com.test.testproject.repo.impl.CryptoRepoImpl
import org.koin.dsl.module

val repoModule = module {

    factory<CryptoRepo> { CryptoRepoImpl(get(), get(), get()) }

}