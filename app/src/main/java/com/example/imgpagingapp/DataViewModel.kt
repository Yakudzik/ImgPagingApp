package com.example.imgpagingapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imgpagingapp.jsonModel.DataImage
import com.example.imgpagingapp.retrofit.ImgAPI
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class DataViewModel(app: Application) : AndroidViewModel(app) {

    private var _categoryNumber = MutableLiveData<ArrayList<DataImage.Data.PostCard>>()
    val categoryNumber: LiveData<ArrayList<DataImage.Data.PostCard>> = _categoryNumber

    private lateinit var response: Response<String>
    var resultSize by Delegates.notNull<Int>()
    lateinit var defaultImg: DataImage.Data.PostCard.Image
    lateinit var defaultPostCArd: DataImage.Data.PostCard


    fun getImagesResponse(page: Int) {
        ImgAPI.invoke().getJoke(2, 2, page).enqueue(object : Callback<DataImage.Data?> {
            override fun onResponse(
                call: Call<DataImage.Data?>,
                response: Response<DataImage.Data?>
            ) {
                if (response.isSuccessful) {
                    resultSize = response.body()!!.postCard.size

                    for (i in 0..resultSize) {

                        defaultPostCArd.let {
                            it.id = i
                            defaultImg.url =
                                "http://static.wizl.itech-mobile.ru/" + response.body()!!.postCard[i].image.url
                            it.image = defaultImg
                        }
                        _categoryNumber.value!!.add(defaultPostCArd)
                    }
                }
            }

            override fun onFailure(call: Call<DataImage.Data?>, t: Throwable) {
            }
        })
    }
}