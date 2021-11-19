package com.example.imgpagingapp.retrofit

 import com.example.imgpagingapp.paging.DataImage
  import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImgAPI {
    @Headers(
        "Authorization: Basic bW9iaWxlY2xpZW50OkxXdzhudzRjdlNvN0tkQlNWZ0Fq",
        "Accept-Language: ru_RU",
        "Content-Type: application/json"
    )
    @GET("/api/v2/lifestyle/feed")
    fun getImgsLinks(
        @Query("addressist_id") addressist: Int,
        @Query("addresist_id") addresist: Int,
        @Query("page") page: Int,
    ): Call<DataImage>

    companion object {
        fun invoke(): ImgAPI {
            //перехватчик для просмотра ответов сервера
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://wizl.itech-mobile.ru/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImgAPI::class.java)
        }
    }
}