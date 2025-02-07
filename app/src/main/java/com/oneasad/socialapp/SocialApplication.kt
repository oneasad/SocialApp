package com.oneasad.socialapp

import android.app.Application
import com.oneasad.socialapp.util.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialApplication)
            modules(appModule)
        }
    }
}