package com.test.testproject.di

import com.test.testproject.vm.CryptoVM
import com.test.testproject.vm.FavoritesVM
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel<CryptoVM>()

    viewModel<FavoritesVM>()

}