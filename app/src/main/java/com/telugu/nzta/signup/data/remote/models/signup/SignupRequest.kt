package com.telugu.nzta.signup.data.remote.models.signup


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SignupRequest(
    @Expose @SerializedName("email")
    var email: String?,
    @Expose @SerializedName("password")
    var password: String?,
    @Expose @SerializedName("phoneNumber")
    var phoneNumber: String?
) : Parcelable