package com.telugu.nzta.signup.di


import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.telugu.nzta.signup.data.remote.source.SignupRemoteDataSource
import com.telugu.nzta.signup.data.remote.source.SignupRemoteDataSourceImpl
import com.telugu.nzta.signup.data.remote.services.SignupService


@Module(includes = [SignupRemoteModule.Binders::class])
class SignupRemoteModule {
    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: SignupRemoteDataSourceImpl
        ): SignupRemoteDataSource
    }

    @Provides
    fun providesSignupService(retrofit: Retrofit): SignupService =
        retrofit.create(SignupService::class.java)


}