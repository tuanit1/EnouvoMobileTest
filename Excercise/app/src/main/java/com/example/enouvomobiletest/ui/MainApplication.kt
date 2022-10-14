package com.example.enouvomobiletest.ui

import android.app.Application
import com.example.enouvomobiletest.data.AppDatabase

class MainApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
}