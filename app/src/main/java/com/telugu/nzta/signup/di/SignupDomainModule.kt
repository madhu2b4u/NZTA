package com.telugu.nzta.signup.di

import com.telugu.nzta.signup.data.repository.SignupRepository
import com.telugu.nzta.signup.data.repository.SignupRepositoryImpl
import com.telugu.nzta.signup.domain.SignUpUseCaseImpl
import com.telugu.nzta.signup.domain.SignUpUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class SignupDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: SignupRepositoryImpl
    ): SignupRepository


    @Binds
    abstract fun bindsArticlesUseCase(
        mSignUpUseCase: SignUpUseCaseImpl
    ): SignUpUseCase


}