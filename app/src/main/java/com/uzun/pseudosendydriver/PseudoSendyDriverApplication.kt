package com.uzun.pseudosendydriver

import android.app.Application
import com.naver.maps.map.NaverMapSdk
import com.uzun.pseudosendydriver.BuildConfig.NAVER_MAP_CLIENT_ID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PseudoSendyDriverApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(NAVER_MAP_CLIENT_ID)
    }
}