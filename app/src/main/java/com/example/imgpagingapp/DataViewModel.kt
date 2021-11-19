package com.example.imgpagingapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.imgpagingapp.paging.DataImage
import com.example.imgpagingapp.paging.PagingSourceAdapteR
import com.example.imgpagingapp.retrofit.ImgAPI
import retrofit2.*

class DataViewModel(app: Application) : AndroidViewModel(app) {


    private lateinit var responseV2: DataImage

    val item = Pager(
        PagingConfig(
            enablePlaceholders = true,
            pageSize = 10
        )
    ) { PagingSourceAdapteR(this) }.flow

    suspend fun getImagesResponse(page: Int): DataImage {

        responseV2 = ImgAPI.invoke().getImgsLinks(1, 2, page).await()

        return responseV2
    }

    fun filterResponse(dataObj: DataImage): DataImage.Data {

        return dataObj.data
    }
}