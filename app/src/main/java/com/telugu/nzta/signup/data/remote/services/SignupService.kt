package com.telugu.nzta.signup.data.remote.services

import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface SignupService {


    @POST("auth/login")
    fun loginAsync(@Body loginPayload: LoginRequest): Deferred<Response<LoginResponse>>

    @POST("auth/signup")
    fun signUpAsync(@Body signUpPayload: SignupRequest): Deferred<Response<LoginResponse>>

}