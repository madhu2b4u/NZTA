package com.telugu.nzta.signup.data.remote.models.login


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Content(
    @Expose @SerializedName("jwt")
    var jwt: String?
) : Parcelable