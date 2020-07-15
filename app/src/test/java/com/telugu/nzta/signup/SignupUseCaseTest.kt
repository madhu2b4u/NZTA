package com.telugu.nzta.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.telugu.nzta.FakeUtils
import com.telugu.nzta.LiveDataTestUtil
import com.telugu.nzta.MainCoroutineRule
import com.telugu.nzta.common.Status
import com.telugu.nzta.common.Result
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.repository.SignupRepository
import com.telugu.nzta.signup.domain.SignUpUseCase
import com.telugu.nzta.signup.domain.SignUpUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SignupUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private var fakeSignup = FakeUtils().fakeSignUp
    private var fakeLogin = FakeUtils().fakeLogin
    private var fakeResponse = FakeUtils().fakeResponse

    lateinit var useCase: SignUpUseCase

    lateinit var repository: SignupRepository



    @Test
    fun testSignUpLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { signup(fakeSignup) } doReturn object :
                LiveData<Result<LoginResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = SignUpUseCaseImpl(repository)

        val result = useCase.signup(fakeSignup)
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testEpisodesSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { signup(fakeSignup) } doReturn object :
                LiveData<Result<LoginResponse>>() {
                init {
                    value = Result.success(fakeResponse)
                }
            }
        }
        useCase = SignUpUseCaseImpl(repository)

        val result = useCase.signup(fakeSignup)

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data ==fakeResponse
        )

    }

    @Test
    fun testSignupErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { signup(fakeSignup) } doReturn object :
                LiveData<Result<LoginResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = SignUpUseCaseImpl(repository)

        val result = useCase.signup(fakeSignup)
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )

    }



}