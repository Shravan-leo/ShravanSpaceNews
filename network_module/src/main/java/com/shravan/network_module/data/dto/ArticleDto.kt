package com.shravan.network_module.data.dto

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    var id : Long? = null,
    var title : String? = null,
    var summary : String? = null,
    @SerializedName("image_url")
    var imageUrl : String? = null,
    @SerializedName("news_site")
    var newsSite : String? = null,

)
