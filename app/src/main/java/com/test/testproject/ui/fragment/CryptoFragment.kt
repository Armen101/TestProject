package com.test.testproject.ui.fragment

import android.os.Bundle
import android.view.View
import com.test.testproject.R
import com.test.testproject.BR
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.test.testproject.databinding.FragmentCryptoBinding
import com.test.testproject.domain.model.Coin
import com.test.testproject.domain.remote.RequestResult
import com.test.testproject.ui.adapter.CoinsAdapter
import com.test.testproject.ui.fragment.core.BindingFragment
import com.test.testproject.vm.CryptoVM

class CryptoFragment : BindingFragment<FragmentCryptoBinding, CryptoVM>() {

    private val vm: CryptoVM by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_crypto

    override fun getViewModel() = vm

    override fun getBindingVariable() = BR.viewModel

    private val adapter = CoinsAdapter({
//        vm.insertCoin()
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.coinsRecyclerView?.adapter = adapter
        vm.updateData.observe(viewLifecycleOwner) { coinsRequestResult ->
            if (coinsRequestResult is RequestResult.OnSuccess<*>) {
                val data = coinsRequestResult.data as List<Coin>
                adapter.appendData(data)
            } else {

            }
        }
    }

}