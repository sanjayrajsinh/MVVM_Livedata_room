package com.example.sanjaysisodiya.componantarchitecturedemo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sanjaysisodiya.componantarchitecturedemo.Model.Order

@Database(entities = [(Order::class)],version = 1)
abstract class MyDatabase : RoomDatabase() {

     abstract fun orderDao():OrderDAO
     companion object {

          private val DB_NAME = "MyDatabse"
          private var roomDB: MyDatabase? = null

          fun getInstanse(context: Context): MyDatabase = roomDB?: synchronized(this) {
               Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, DB_NAME)
                       .fallbackToDestructiveMigration()
                       .build()

          }

     }

}
