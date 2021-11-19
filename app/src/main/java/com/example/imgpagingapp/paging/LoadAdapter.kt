package com.example.imgpagingapp.paging

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imgpagingapp.databinding.ErrorItemBinding

class LoadAdapter(private val retryCallback: () -> Unit) :
    LoadStateAdapter<LoadAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {

        return ViewHolder(
            ErrorItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)

        val layoutParam = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        layoutParam.isFullSpan = true

    }

    inner class ViewHolder(private val binding: ErrorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val duration = 2000L

        init {
            binding.retryButtonID.setOnClickListener { retryCallback.invoke() }
        }

          fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Loading) {
                    progressBar.isVisible = true
                    progressBar.max = 100
                    loadPG(progressBar, int = 100)


                    retryButtonID.isVisible = false
                    errorMessageID.isVisible = false
                } else {
                    progressBar.isVisible = false
                    retryButtonID.isVisible = loadState is LoadState.Error
                    errorMessageID.isVisible = loadState is LoadState.Error
                }
            }
        }

       private   fun loadPG(progressB:ProgressBar,int: Int){
            ObjectAnimator.ofInt(progressB, "progress", int)
                .setDuration(duration)
                .start()
        }
    }
}