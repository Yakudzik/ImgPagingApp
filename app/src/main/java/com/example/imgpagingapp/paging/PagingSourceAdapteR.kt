package com.example.imgpagingapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imgpagingapp.DataViewModel
import com.example.imgpagingapp.jsonModel.DataImage
import retrofit2.HttpException
import java.lang.Exception

class PagingSourceAdapteR(private val viewModel: DataViewModel) :PagingSource<Int,DataImage.Data.PostCard>() {
    override fun getRefreshKey(state: PagingState<Int, DataImage.Data.PostCard>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataImage.Data.PostCard> {
        return try {
            val pagePosition = params.key ?: 0
            val pageSize = viewModel.
            Log.i("pagePosition", pagePosition.toString())
            viewModel.getImagesResponse(pagePosition)

            LoadResult.Page(
                    data = loadSize,
                    prevKey = if (pagePosition == 0) null else pagePosition - 1,
                    nextKey = if (loadSize.isNullOrEmpty()) null else pagePosition + 1
                )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}