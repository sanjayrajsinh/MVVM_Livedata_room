package com.example.sanjaysisodiya.componantarchitecturedemo.ViewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.sanjaysisodiya.componantarchitecturedemo.Database.MyDatabase
import com.example.sanjaysisodiya.componantarchitecturedemo.Database.OrderDAO
import com.example.sanjaysisodiya.componantarchitecturedemo.Model.Order

class OrderRepository  {

     var orderdao:OrderDAO?=null
     var orderList : LiveData<List<Order>>?=null

     constructor(application: Application){
          var mydb=MyDatabase.getInstanse(application.applicationContext)
          orderdao=mydb.orderDao()
          orderList=orderdao?.getAllOrders()
     }

     fun insert(order: Order){
          InsertAsync(orderdao).execute(order)
     }
     fun delete(order: Order){
          DeleteAsync(orderdao).execute(order)
     }
     fun getAllOrders():LiveData<List<Order>>?{
          return orderList
     }

     class InsertAsync(var dao: OrderDAO?) : AsyncTask<Order, Void, Void>() {
          override fun doInBackground(vararg params: Order): Void? {
               dao?.insertOrder(params.get(0))
               return null
          }
     }
     class DeleteAsync(var dao: OrderDAO?):AsyncTask<Order,Void,Void>(){
          override fun doInBackground(vararg params: Order): Void? {
               dao?.deleteOrder(params.get(0))
               return null
          }
     }
}