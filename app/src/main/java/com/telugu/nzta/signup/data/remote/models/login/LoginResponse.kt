package com.telugu.nzta.signup.data.remote.models.login


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LoginResponse(
    @Expose @SerializedName("content")
    var content: Content?,
    @Expose @SerializedName("error")
    var error: Boolean?,
    @Expose @SerializedName("message")
    var message: String?
) : Parcelable