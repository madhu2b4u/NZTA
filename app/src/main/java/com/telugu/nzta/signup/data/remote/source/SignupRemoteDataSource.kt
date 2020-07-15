package com.telugu.nzta.signup.data.remote.source

import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest


interface SignupRemoteDataSource {


    suspend fun signup(signupPayload: SignupRequest): LoginResponse
    suspend fun login(loginPayload: LoginRequest): LoginResponse


}