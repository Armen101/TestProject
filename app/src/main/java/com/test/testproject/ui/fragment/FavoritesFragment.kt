package com.test.testproject.ui.fragment

import com.test.testproject.BR
import com.test.testproject.R
import com.test.testproject.databinding.FragmentFavoritesBinding
import com.test.testproject.ui.fragment.core.BindingFragment
import com.test.testproject.vm.FavoritesVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BindingFragment<FragmentFavoritesBinding, FavoritesVM>() {

    private val vm: FavoritesVM by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_favorites

    override fun getViewModel() = vm

    override fun getBindingVariable() = BR.viewModel

}