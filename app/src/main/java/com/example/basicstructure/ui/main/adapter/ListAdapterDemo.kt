package com.example.basicstructure.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicstructure.R
import com.example.basicstructure.data.model.Photo
import com.example.basicstructure.databinding.ItemHotProductBinding
import com.example.basicstructure.util.clickWithDebounce

class ListAdapterDemo(private var listener: ClickListener) :
    ListAdapter<Photo, ListAdapterDemo.ListAdapterDemoVH>(DiffCallback) {

    interface ClickListener {
        fun itemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterDemoVH =
        ListAdapterDemoVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_hot_product,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListAdapterDemoVH, position: Int) {
        holder.bind(getItem(position))
        holder.binding.imgBanner.clickWithDebounce { listener.itemClick(position) }
    }

    class ListAdapterDemoVH(var binding: ItemHotProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: Photo) = binding.apply {
            mData = itemData
            executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }

}