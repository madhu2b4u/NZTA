package com.telugu.nzta.signup.domain

import androidx.lifecycle.LiveData
import com.telugu.nzta.common.Result
import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest
import com.telugu.nzta.signup.data.repository.SignupRepository
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(private val mSignupRepository: SignupRepository) :
    SignUpUseCase {
    override suspend fun login(loginPayload: LoginRequest)=  mSignupRepository.login(loginPayload)
    override suspend fun signup(signupPayload: SignupRequest)=  mSignupRepository.signup(signupPayload)
}
