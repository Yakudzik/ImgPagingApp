package com.example.imgpagingapp.jsonModel


import com.google.gson.annotations.SerializedName

data class DataImage(
//    @SerializedName("status")
//    val status: Int,
    @SerializedName("data")
    val data: Data

) {
    data class Data(
        @SerializedName("post_card")
        val postCard: List<PostCard>
    ) {
        data class PostCard(
            @SerializedName("id")
            var id: Int,
//            @SerializedName("name")
//            val name: String,
//            @SerializedName("is_author")
//            val isAuthor: Int,
            @SerializedName("image")
            var image: Image
        ) {
            data class Image(
                @SerializedName("url")
                var url: String,
//
//                @SerializedName("video")
//                val video: Any?,
//                @SerializedName("preview")
//                val preview: String,
//                @SerializedName("webp")
//                val webp: String,
//                @SerializedName("webp_preview")
//                val webpPreview: String
            )
        }
    }
}