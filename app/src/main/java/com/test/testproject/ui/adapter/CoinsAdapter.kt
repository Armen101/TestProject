package com.test.testproject.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.test.testproject.R
import com.test.testproject.BR
import com.test.testproject.domain.model.Coin
import java.text.NumberFormat
import java.util.*

class CoinsAdapter(private val onStarClickListener: (Coin) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Coin?>()

    @SuppressLint("NotifyDataSetChanged")
    fun appendData(adapterData: List<Coin>) {
        if (!data.isNullOrEmpty() && data.last() == null) {
            data.removeLast()
        }
        data.addAll(adapterData)
        data.add(null)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] == null && position == (data.size - 1)) {
            LOAD_MORE_VIEW_TYPE
        } else {
            REGULAR_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == REGULAR_VIEW_TYPE) {
            val rootView = inflater.inflate(R.layout.row_coin, parent, false)
            CoinsVH(rootView)
        } else {
            val rootView = inflater.inflate(R.layout.row_loading, parent, false)
            LoadingVH(rootView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CoinsVH) {
            holder.bind(data[position])
        }
    }

    override fun getItemCount() = data.size

    inner class CoinsVH(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: ViewDataBinding? = DataBindingUtil.bind(view)

        fun bind(coin: Coin?) {
            binding?.apply {
                setVariable(BR.coin, coin)
                setVariable(BR.holder, this@CoinsVH)
                executePendingBindings()
            }
        }

        fun getFormattedCurrency(price: Double): String {
            val currency = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(price)
            return currency.replace("$", "").trim()
        }

        fun onStarClick(coin: Coin) {
            onStarClickListener(coin)
        }
    }

    inner class LoadingVH(view: View) : RecyclerView.ViewHolder(view)

    private companion object {

        const val REGULAR_VIEW_TYPE = 0
        const val LOAD_MORE_VIEW_TYPE = 1
    }

}