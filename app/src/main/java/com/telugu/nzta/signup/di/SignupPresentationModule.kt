package com.telugu.nzta.signup.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.telugu.nzta.common.ViewModelFactory
import com.telugu.nzta.di.ViewModelKey
import com.telugu.nzta.signup.presentation.viewmodel.SignupViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignupPresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel::class)
    abstract fun bindsHomeViewModel(mSignupViewModel: SignupViewModel): ViewModel
}