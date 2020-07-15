package com.telugu.nzta.di

import android.app.Application
import com.telugu.nzta.NztaApp
import com.telugu.nzta.signup.di.SignupDomainModule
import com.telugu.nzta.signup.di.SignupPresentationModule
import com.telugu.nzta.signup.di.SignupRemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,
        AppModule::class,SignupDomainModule::class,SignupRemoteModule::class,SignupPresentationModule::class
    ]
)
interface NztaAppComponent : AndroidInjector<NztaApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): NztaAppComponent
    }

    override fun inject(app: NztaApp)
}