package com.sanjay.imgur.data.repository.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class ImgurSearchResponse(
    @SerializedName("data") val images: List<ImgurImage>
)

@Parcelize
data class ImgurImage(
    val id: String,
    val title: String,
    @SerializedName("ups") val likes: Int,
    @SerializedName("comment_count") val comments: Int,
    @SerializedName("favorite_count") val favorites: Int,
    val cover: String

) : Parcelable
