package com.example.composecoursety

import android.app.Application

class ComposeUIApplication : Application() {

    companion object {
         lateinit var mContext: ComposeUIApplication
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}