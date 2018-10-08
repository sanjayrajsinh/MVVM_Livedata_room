package com.example.sanjaysisodiya.componantarchitecturedemo.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sanjaysisodiya.componantarchitecturedemo.Model.Order

class OrderViewModel : AndroidViewModel {

     var repo: OrderRepository? = null
     var orderlist: LiveData<List<Order>>? = null

     constructor(application: Application) : super(application) {

          repo = OrderRepository(application)
          orderlist = repo!!.getAllOrders()
     }

     fun getOrderLiveData(): LiveData<List<Order>>? = orderlist

     fun insert(order: Order) {
          repo!!.insert(order)
     }

     fun delete(order: Order) {
          repo!!.delete(order)
     }

}