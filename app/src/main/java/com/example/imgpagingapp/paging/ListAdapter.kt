package com.example.imgpagingapp.paging

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.imgpagingapp.R
import com.example.imgpagingapp.databinding.ListItemBinding

class ListAdapter(val glidersFragment: Fragment) :
    PagingDataAdapter<DataImage.Data.PostCard, ListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private val LOG_TAG = "listAdapter"

    companion object {
        private var DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataImage.Data.PostCard>() {
            override fun areItemsTheSame(
                oldItem: DataImage.Data.PostCard,
                newItem: DataImage.Data.PostCard
            )
                    : Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: DataImage.Data.PostCard,
                newItem: DataImage.Data.PostCard
            )
                    : Boolean = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: DataImage.Data.PostCard? = getItem(position)

        if (currentItem != null) {
            //holder.itemView.layout(0,0,0,0)
            holder.bunTo(currentItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d(LOG_TAG, "create viewHolder")
        return MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class MyViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var imageView = binding.listItemImageID

        fun bunTo(someItem: DataImage.Data.PostCard) {

            Log.i("Image text", someItem.image.url)

            val wid = someItem.image.dimentions.width
            val hei = someItem.image.dimentions.height


            Glide.with(glidersFragment)
                .load("http://static.wizl.itech-mobile.ru" + someItem.image.url)
                .placeholder(R.drawable.placeholderimage)
                .error(ColorDrawable(Color.GRAY))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .override(wid, hei)
                .fitCenter()
                .priority(Priority.IMMEDIATE)
                .into(imageView)

        }

    }
}