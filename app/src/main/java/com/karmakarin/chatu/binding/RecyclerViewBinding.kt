package com.karmakarin.chatu.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karmakarin.chatu.data.model.CM
import com.karmakarin.chatu.ui.main.adapter.ChatAdapter

object RecyclerViewBinding {


    @JvmStatic
    @BindingAdapter("adapterFresh", "submitList", requireAll = true)
    fun bindRecyclerView(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
        itemList: List<CM>?
    ) {
        try {
            recyclerView.adapter = adapter
            if (itemList != null) {
                (adapter as ChatAdapter).refreshList(itemList) // Ensure `refreshList` exists in `ChatAdapter`
                recyclerView.smoothScrollToPosition(itemList.size)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    @BindingAdapter("layoutManager", "spanCount", requireAll = false)
    fun setLayoutManager(recyclerView: RecyclerView, layoutManager: String?, spanCount: Int?) {
        recyclerView.layoutManager = when (layoutManager) {
            "LinearLayoutManager" -> LinearLayoutManager(recyclerView.context).apply {
                stackFromEnd = true
            }

            "GridLayoutManager" -> GridLayoutManager(recyclerView.context, spanCount ?: 2)
            else -> LinearLayoutManager(recyclerView.context)
        }
    }

    @JvmStatic
    @BindingAdapter("paginationPokemonList")
    fun bindPagination(recyclerView: RecyclerView, viewModel: ViewModel) {
        // Set up scroll listener or pagination here using the viewModel
    }
}