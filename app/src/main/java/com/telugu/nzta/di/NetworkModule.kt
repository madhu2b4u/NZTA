package com.telugu.nzta.di

import android.util.Log
import com.telugu.nzta.di.qualifiers.IO
import com.telugu.nzta.di.qualifiers.MainThread
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.telugu.nzta.BuildConfig
import com.telugu.nzta.common.SpUtil
import com.telugu.nzta.common.TOKEN
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

@Module
class NetworkModule {

    private val TAG = "Apifactory"


    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient())
            .build()


    @IO
    @Provides
    fun providesIoDispatcher(): CoroutineContext = Dispatchers.IO

    @MainThread
    @Provides
    fun providesMainThreadDispatcher(): CoroutineContext = Dispatchers.Main

    private fun provideOkHttpClient(): OkHttpClient? {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient().newBuilder()
        client.connectTimeout(50000, TimeUnit.MILLISECONDS)
        client.readTimeout(50000, TimeUnit.MILLISECONDS)
        if (!SpUtil.instance?.getString(TOKEN, "").isNullOrEmpty()) {
            Log.e(TAG, "onCreate: ${SpUtil.instance!!.getString(TOKEN)}")
            client.addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", SpUtil.instance?.getString(TOKEN).toString())
                    .build()
                chain.proceed(newRequest)

            }
        }
        client.addInterceptor(logging)
        return client.build()
    }

}