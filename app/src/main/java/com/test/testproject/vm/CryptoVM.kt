package com.test.testproject.vm

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.testproject.domain.model.Coin
import com.test.testproject.domain.remote.RequestResult
import com.test.testproject.repo.CryptoRepo
import com.test.testproject.ui.adapter.pagination.PaginationListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CryptoVM(application: Application, private val cryptoRepo: CryptoRepo) :
    AndroidViewModel(application), PaginationListener {

    val isLoading = ObservableBoolean(true)
    private val _updateData = MutableLiveData<RequestResult>()
    val updateData: LiveData<RequestResult> = _updateData

    init {
        getCoinsData(0)
    }

    fun insertCoin(coin: Coin) {
//        cryptoRepo.insertCoin(coin)
    }

    private fun getCoinsData(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = cryptoRepo.getCoins(page, PAGINATION_SIZE)
                _updateData.postValue(RequestResult.OnSuccess(response.coins ?: emptyList()))
                isLoading.set(false)
            } catch (e: Exception) {
                isLoading.set(false)
                _updateData.postValue(RequestResult.OnError(e))
            }
        }
    }

    override fun onPageChanged(page: Int) {
        getCoinsData(page)
    }

    private companion object {

        const val PAGINATION_SIZE = 20
    }

}