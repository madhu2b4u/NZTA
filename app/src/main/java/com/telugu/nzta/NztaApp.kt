package com.telugu.nzta
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.analytics.FirebaseAnalytics
import com.telugu.nzta.common.SpUtil
import com.telugu.nzta.di.DaggerNztaAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NztaApp : DaggerApplication() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerNztaAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        SpUtil.instance?.init(this)
        Fresco.initialize(this);
    }


}