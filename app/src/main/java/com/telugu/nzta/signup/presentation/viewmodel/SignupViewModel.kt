package com.telugu.nzta.signup.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.domain.SignUpUseCase
import javax.inject.Inject
import com.telugu.nzta.common.Result
import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest
import kotlinx.coroutines.launch


class SignupViewModel @Inject constructor(
    private val mSignUpUseCase: SignUpUseCase) : ViewModel() {

    val loginResult = MediatorLiveData<Result<LoginResponse>>()
    val signupResult = MediatorLiveData<Result<LoginResponse>>()


    fun login(loginPayload: LoginRequest) {
        viewModelScope.launch {
            loginResult.addSource(mSignUpUseCase.login(loginPayload)) {
                loginResult.value = it
            }
        }
    }

    fun signup(signupPayload: SignupRequest) {
        viewModelScope.launch {
            signupResult.addSource(mSignUpUseCase.signup(signupPayload)) {
                signupResult.value = it
            }
        }
    }



}