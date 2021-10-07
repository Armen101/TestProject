package com.test.testproject.vm

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.test.testproject.repo.CryptoRepo

class FavoritesVM(application: Application, private val cryptoRepo: CryptoRepo) :
    AndroidViewModel(application) {

    val isLoading = ObservableBoolean(false)

}