package com.test.testproject.ui.binding

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.testproject.ui.adapter.pagination.EndlessRecyclerViewScrollListener
import com.test.testproject.ui.adapter.pagination.PaginationListener

@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("srcRemoteUrl")
fun srcRemoteUrl(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .into(view)
}

@BindingAdapter("paginationListener")
fun setPaginationListener(rv: RecyclerView, listener: PaginationListener) {
    val lm = rv.layoutManager
    if (lm !is LinearLayoutManager) return
    val scrollListener = object : EndlessRecyclerViewScrollListener(lm) {
        override fun onLoadMore(page: Int) {
            listener.onPageChanged(page)
        }
    }
    rv.addOnScrollListener(scrollListener)
}