package com.example.sanjaysisodiya.componantarchitecturedemo.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.sanjaysisodiya.componantarchitecturedemo.Model.Order

@Dao
interface OrderDAO {

     @Query("SELECT * FROM orders")
     fun getAllOrders() :LiveData<List<Order>>

     @Delete
     fun deleteOrder(order: Order)

     @Insert
     fun insertOrder(order: Order)

}