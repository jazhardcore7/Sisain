package com.sisain.capstone.data.userlocaldb

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_db").build()
    }

    companion object {
        lateinit var appDatabase: AppDatabase
    }
}