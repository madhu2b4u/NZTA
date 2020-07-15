package com.telugu.nzta.signup.data.remote.source

import com.telugu.nzta.di.qualifiers.IO
import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.telugu.nzta.signup.data.remote.services.SignupService


class SignupRemoteDataSourceImpl @Inject constructor(
    private val service: SignupService,
    @IO private val context: CoroutineContext
) : SignupRemoteDataSource {
    override suspend fun signup(signupPayload: SignupRequest)= withContext(context) {
        val response = service.signUpAsync(signupPayload).await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no response")
        else
            throw Exception("invalid request with code ${response.code()}")
    }

    override suspend fun login(loginPayload: LoginRequest)= withContext(context) {
        val response = service.loginAsync(loginPayload).await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no response")
        else
            throw Exception("invalid request with code ${response.code()}")
    }

}
