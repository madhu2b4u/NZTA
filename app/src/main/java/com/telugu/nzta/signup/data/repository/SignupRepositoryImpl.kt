package com.telugu.nzta.signup.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest
import javax.inject.Inject
import com.telugu.nzta.signup.data.remote.source.SignupRemoteDataSource
import com.telugu.nzta.common.Result


class SignupRepositoryImpl @Inject constructor(private val remoteDataSource: SignupRemoteDataSource) : SignupRepository {

    override suspend fun login(loginPayload: LoginRequest) = liveData {
        emit(Result.loading())
        try {
            val response = remoteDataSource.login(loginPayload)
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }

    override suspend fun signup(signupPayload: SignupRequest) = liveData {
        emit(Result.loading())
        try {
            val response = remoteDataSource.signup(signupPayload)
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }


}