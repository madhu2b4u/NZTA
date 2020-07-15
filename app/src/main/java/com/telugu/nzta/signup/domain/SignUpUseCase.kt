package com.telugu.nzta.signup.domain

import androidx.lifecycle.LiveData
import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest
import com.telugu.nzta.common.Result


interface SignUpUseCase {

    suspend fun login(loginPayload: LoginRequest): LiveData<Result<LoginResponse>>
    suspend fun signup(signupPayload: SignupRequest): LiveData<Result<LoginResponse>>

}