package com.example.imgpagingapp.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imgpagingapp.databinding.ListItemBinding
import com.example.imgpagingapp.jsonModel.DataImage
import com.squareup.picasso.Picasso

class ListAdapter : PagingDataAdapter<DataImage.Data.PostCard, ListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private val LOG_TAG = "listAdapter"

    companion object {
        private var DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataImage.Data.PostCard>() {
            override fun areItemsTheSame(oldItem: DataImage.Data.PostCard, newItem: DataImage.Data.PostCard)
                    : Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DataImage.Data.PostCard, newItem: DataImage.Data.PostCard)
                    : Boolean = oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: DataImage.Data.PostCard? = getItem(position)

        holder.bunTo(currentItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d(LOG_TAG, "create viewHolder")
        return MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class MyViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var imageView = binding.listItemImageID

        fun bunTo(baseImage: DataImage.Data.PostCard?) {

            Picasso.get()
                .load(baseImage!!.image.url)
                .into(imageView);
        }
    }
}