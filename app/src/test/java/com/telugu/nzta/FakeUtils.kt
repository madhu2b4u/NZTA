package com.telugu.nzta

import com.telugu.nzta.signup.data.remote.models.login.Content
import com.telugu.nzta.signup.data.remote.models.login.LoginRequest
import com.telugu.nzta.signup.data.remote.models.login.LoginResponse
import com.telugu.nzta.signup.data.remote.models.signup.SignupRequest

class FakeUtils {

    val fakeSignUp = SignupRequest("xyz@gmail.com","1234567","0211987031")
    val fakeResponse = LoginResponse(Content("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Imhlcm9rdXVzZXIyQGV4YW1wbGUuY29tIiwiaWF0IjoxNTk0Nzg0NDE4LCJleHAiOjE1OTQ4MjA0MTh9.JJyrJfbMf4FY6vdEGeZ2B_-gT5Jdy-hK1FQPJ0VSt5o"),false,"User verified Successfully!")
    val fakeLogin = LoginRequest("xyz@gmail.com","1234567")


}