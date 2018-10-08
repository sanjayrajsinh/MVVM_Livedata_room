package com.example.sanjaysisodiya.componantarchitecturedemo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
class Order {

     @PrimaryKey
     var id:Int?=null
     var name:String?=null
     var qty:Int?=null
     var image:String?=null

}