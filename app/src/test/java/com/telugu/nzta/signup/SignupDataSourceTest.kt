package com.telugu.nzta.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.telugu.nzta.FakeUtils
import com.telugu.nzta.MainCoroutineRule
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.services.SignupService
import com.telugu.nzta.signup.data.remote.source.SignupRemoteDataSource
import com.telugu.nzta.signup.data.remote.source.SignupRemoteDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SignupDataSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var remoteDataSource: SignupRemoteDataSource

    lateinit var signupService: SignupService

    @Before
    fun init() {

        signupService = mock {
            onBlocking { signUpAsync(FakeUtils().fakeSignUp) } doReturn GlobalScope.async {
                Response.success(FakeUtils().fakeResponse)
            }
        }

        remoteDataSource = SignupRemoteDataSourceImpl(signupService, mainCoroutineRule.coroutineContext)


    }

    @Test
    fun testSignup() = runBlocking {

        signupService = mock {
            onBlocking { signUpAsync(FakeUtils().fakeSignUp) } doReturn GlobalScope.async {
                Response.success(FakeUtils().fakeResponse)
            }
        }

        remoteDataSource = SignupRemoteDataSourceImpl(signupService, mainCoroutineRule.coroutineContext)
        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.signup(FakeUtils().fakeSignUp)
        assert(result == FakeUtils().fakeResponse)


    }


    @Test
    fun testLogin() = runBlocking {

        signupService = mock {
            onBlocking { loginAsync(FakeUtils().fakeLogin) } doReturn GlobalScope.async {
                Response.success(FakeUtils().fakeResponse)
            }
        }

        remoteDataSource = SignupRemoteDataSourceImpl(signupService, mainCoroutineRule.coroutineContext)
        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.login(FakeUtils().fakeLogin)
        assert(result == FakeUtils().fakeResponse)
    }

    @Test(expected = Exception::class)
    fun testSignUpThrowWeatherException() = runBlocking {

        signupService = mock {
            onBlocking { signUpAsync(FakeUtils().fakeSignUp) } doReturn GlobalScope.async {
                Response.error<LoginResponse>(404, null)
            }
        }

        remoteDataSource = SignupRemoteDataSourceImpl(signupService, mainCoroutineRule.coroutineContext)
        assert(remoteDataSource.signup(FakeUtils().fakeSignUp) == FakeUtils().fakeResponse)

    }


    @Test(expected = Exception::class)
    fun testLoginThrowWeatherException() = runBlocking {

        signupService = mock {
            onBlocking { loginAsync(FakeUtils().fakeLogin) } doReturn GlobalScope.async {
                Response.error<LoginResponse>(404, null)
            }
        }

        remoteDataSource = SignupRemoteDataSourceImpl(signupService, mainCoroutineRule.coroutineContext)
        assert(remoteDataSource.login(FakeUtils().fakeLogin) == FakeUtils().fakeResponse)

    }


}