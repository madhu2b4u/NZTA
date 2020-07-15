package com.telugu.nzta.di

import com.telugu.nzta.signup.presentation.ui.fragments.SignupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilderModule {


    @ContributesAndroidInjector
    abstract fun contributesSignupFragment(): SignupFragment
}