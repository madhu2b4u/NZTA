package com.telugu.nzta.di

import com.telugu.nzta.MainActivity
import com.telugu.nzta.signup.presentation.ui.activity.SignupActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributesSignupActivity(): SignupActivity
}