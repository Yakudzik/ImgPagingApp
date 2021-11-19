package com.example.imgpagingapp

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imgpagingapp.databinding.FragmentContentBinding
import com.example.imgpagingapp.paging.ListAdapter
import com.example.imgpagingapp.paging.LoadAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ContentFragment : Fragment() {
    private lateinit var binding: FragmentContentBinding
    private lateinit var adapter: ListAdapter
    private val model: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Test"

        adapter = ListAdapter(this)

        val sgl = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        sgl.gapStrategy = (StaggeredGridLayoutManager.GAP_HANDLING_NONE)

        val recyclerView = binding.recyclerID
        recyclerView.layoutManager = sgl
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadAdapter { adapter.retry() },
            footer = LoadAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.apply {
                recyclerID.isVisible = state.refresh != LoadState.Loading
                mainLoadingID.isVisible = state.refresh == LoadState.Loading
            }
            state.source.refresh.endOfPaginationReached
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            model.item.collectLatest {
                adapter.submitData(it)
                adapter.itemCount
            }
        }
    }
}