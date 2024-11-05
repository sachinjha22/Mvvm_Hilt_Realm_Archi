package com.karmakarin.chatu.ui.main.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karmakarin.chatu.data.model.CM
import com.karmakarin.chatu.databinding.ItemReceiverBinding
import com.karmakarin.chatu.databinding.ItemSenderBinding
import com.karmakarin.chatu.ui.main.activity.MainActivity

class ChatAdapter(
    list: ArrayList<CM>?,
    private val mainActivity: MainActivity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var showList = ArrayList<CM>()
    private val sender = 1
    private val receiver = 2

    init {
        showList.clear()
        list?.let { showList.addAll(it) }
    }

    fun refreshList(list: List<CM>?) {
        showList.clear()
        list?.let { showList.addAll(it) }
        kotlin.run {
            notifyDataSetChanged()
        }
    }

    internal class SentViewHolder(private val binding: ItemSenderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CM) {
            with(binding) {
                tvMessage.text = item.content
                tvDate.text = item.timestamp.toString()
                if (!TextUtils.isEmpty(item.gd)) {
                    rlGroup.visibility = View.VISIBLE
                    tvGroupDate.text = item.gd
                }
            }
        }
    }

    internal class ReceiverViewHolder(private val binding: ItemReceiverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CM) {
            with(binding) {
                tvMessage.text = item.content
                tvDate.text = item.timestamp.toString()
                if (!TextUtils.isEmpty(item.gd)) {
                    rlGroup.visibility = View.VISIBLE
                    tvGroupDate.text = item.gd
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == sender) {
            val binding =
                ItemSenderBinding.inflate(LayoutInflater.from(mainActivity), parent, false)
            SentViewHolder(binding)
        } else {
            val binding =
                ItemReceiverBinding.inflate(LayoutInflater.from(mainActivity), parent, false)
            ReceiverViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = showList[position]
        return if (item.sender!!) {
            sender
        } else {
            receiver
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = showList[position]
        when (holder) {
            is SentViewHolder -> {
                holder.bind(item)
            }
            is ReceiverViewHolder -> {
                holder.bind(item)
            }
            else -> throw IllegalArgumentException("Unknown ViewHolder type")
        }
    }
}