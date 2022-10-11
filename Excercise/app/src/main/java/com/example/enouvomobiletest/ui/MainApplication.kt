package com.example.enouvomobiletest.ui

import android.app.Application
import com.example.enouvomobiletest.data.AppDatabase
import com.example.enouvomobiletest.data.repository.PostRepo
import com.example.enouvomobiletest.data.repository.UserRepo

class MainApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
//    val userRepo by lazy { UserRepo(database.getUserDao()) }
//    val postRepo by lazy { PostRepo(database.getPostDao()) }

}