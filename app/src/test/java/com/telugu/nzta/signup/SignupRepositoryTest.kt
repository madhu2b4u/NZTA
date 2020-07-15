package com.telugu.nzta.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.telugu.nzta.FakeUtils
import com.telugu.nzta.LiveDataTestUtil
import com.telugu.nzta.MainCoroutineRule
import com.telugu.nzta.common.Status
import com.telugu.nzta.signup.data.remote.source.SignupRemoteDataSource
import com.telugu.nzta.signup.data.repository.SignupRepository
import com.telugu.nzta.signup.data.repository.SignupRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class SignupRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: SignupRepository

    @Mock
    lateinit var remoteDataSource: SignupRemoteDataSource


    private var fakeSignup = FakeUtils().fakeSignUp
    private var fakeLogin = FakeUtils().fakeLogin
    private var fakeResponse = FakeUtils().fakeResponse

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = SignupRepositoryImpl(remoteDataSource)
    }

    @Test
    fun testSignUp() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.signup(fakeSignup)).thenReturn(fakeResponse)
        val result = repository.signup(fakeSignup)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == fakeResponse)
    }

    @Test(expected = Exception::class)
    fun testGetSignupThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.signup(fakeSignup))
        repository.signup(fakeSignup)

    }


    @Test
    fun testLogin() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.login(fakeLogin)).thenReturn(fakeResponse)
        val result = repository.login(fakeLogin)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == fakeResponse)
    }

    @Test(expected = Exception::class)
    fun testGetLoginThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.login(fakeLogin))
        repository.login(fakeLogin)

    }




}
