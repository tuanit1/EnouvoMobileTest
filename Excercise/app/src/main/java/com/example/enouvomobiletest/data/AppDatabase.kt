package com.example.enouvomobiletest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.enouvomobiletest.data.dao.PostDao
import com.example.enouvomobiletest.data.dao.UserDao
import com.example.enouvomobiletest.data.model.FavoritePosts
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Database(
    entities = [User::class, Post::class, FavoritePosts::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao
    abstract fun getUserDao(): UserDao

    class AppDatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val applicationScope = CoroutineScope(SupervisorJob())

            INSTANCE?.let { database ->
                applicationScope.launch {
                    val userDao = database.getUserDao()
                    val postDao = database.getPostDao()

                    initUser(userDao)
                    initPost(postDao)
                }
            }

        }

        private suspend fun initPost(postDao: PostDao) {
            postDao.deleteAll()

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
            val currentDate = sdf.format(Date())

            postDao.insert(Post(null,  1, "Content1", currentDate))
            postDao.insert(Post(null,  2, "Content2", currentDate))
            postDao.insert(Post(null,  3, "Content3", currentDate))
            postDao.insert(Post(null,  4, "Content4", currentDate))
            postDao.insert(Post(null,  1, "Content5", currentDate))
            postDao.insert(Post(null,  1, "Content6", currentDate))
            postDao.insert(Post(null,  1, "Content7", currentDate))
            postDao.insert(Post(null,  1, "Content8", currentDate))
            postDao.insert(Post(null,  2, "Content9", currentDate))
            postDao.insert(Post(null,  2, "Content10", currentDate))
            postDao.insert(Post(null,  2, "Content11", currentDate))
            postDao.insert(Post(null,  2, "Content12", currentDate))
            postDao.insert(Post(null,  3, "Content13", currentDate))
            postDao.insert(Post(null,  3, "Content14", currentDate))
            postDao.insert(Post(null,  3, "Content15", currentDate))
            postDao.insert(Post(null,  3, "Content16", currentDate))
            postDao.insert(Post(null,  4, "Content17", currentDate))
            postDao.insert(Post(null,  4, "Content18", currentDate))
            postDao.insert(Post(null,  4, "Content19", currentDate))
            postDao.insert(Post(null,  4, "Content20", currentDate))



        }

        private suspend fun initUser(userDao: UserDao){
            userDao.deleteAll()

            userDao.insert(User(user_id = null, name = "Tuan", email = "tuan@gmail.com", password = "Tuan@123"))
            userDao.insert(User(user_id = null, name = "Huy", email = "huy@gmail.com", password = "Tuan@123"))
            userDao.insert(User(user_id = null, name = "Hoang", email = "hoang@gmail.com", password = "Tuan@123"))
            userDao.insert(User(user_id = null, name = "Tuyen", email = "tuyen@gmail.com", password = "Tuan@123"))
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}