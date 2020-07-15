package com.telugu.nzta.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.telugu.nzta.FakeUtils
import com.telugu.nzta.LiveDataTestUtil
import com.telugu.nzta.MainCoroutineRule
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.domain.SignUpUseCase
import com.telugu.nzta.signup.presentation.viewmodel.SignupViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.telugu.nzta.common.Status
import com.telugu.nzta.common.Result

@ExperimentalCoroutinesApi
class SignupViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: SignupViewModel
    lateinit var useCase: SignUpUseCase

    private var fakeSignup = FakeUtils().fakeSignUp
    private var fakeLogin = FakeUtils().fakeLogin
    private var fakeResponse = FakeUtils().fakeResponse

    @Before
    fun init() {
        useCase = mock ()
    }

    @Test
    fun testSignupLoadingData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { signup(fakeSignup) } doReturn object :
                LiveData<Result<LoginResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = SignupViewModel(useCase)
        viewModel.signup(fakeSignup)
        val result = viewModel.signupResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(viewModel.signupResult).status == Status.LOADING)

    }

    @Test
    fun testSignupSuccessData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { signup(fakeSignup) } doReturn object :
                LiveData<Result<LoginResponse>>() {
                init {
                    value = Result.success(fakeResponse)
                }
            }
        }

        viewModel = SignupViewModel(useCase)
        viewModel.signup(fakeSignup)
        val result = viewModel.signupResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == fakeResponse
        )
    }

    @Test
    fun testCategoryErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { signup(fakeSignup) } doReturn object :
                LiveData<Result<LoginResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = SignupViewModel(useCase)
        viewModel.signup(fakeSignup)
        val result = viewModel.signupResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }



}