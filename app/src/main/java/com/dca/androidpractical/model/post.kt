package com.dca.androidpractical.model

import com.google.gson.annotations.SerializedName

data class post(
    @SerializedName("id")
    val id: String?,
    @SerializedName("posttype")
    val posttype: Int?,
    @SerializedName("thumb")
    val thumb: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("likes")
    val likes: String?,
    @SerializedName("comments")
    val comments: String?,
    @SerializedName("userName")
    val userName: String?,
    @SerializedName("userImage")
    val userImage: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("width")
    val width: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("blurImage")
    val blurImage: String?

)