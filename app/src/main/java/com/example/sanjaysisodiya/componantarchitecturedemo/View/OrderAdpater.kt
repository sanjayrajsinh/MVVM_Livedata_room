package com.example.sanjaysisodiya.componantarchitecturedemo.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanjaysisodiya.componantarchitecturedemo.Model.Order
import com.example.sanjaysisodiya.componantarchitecturedemo.R

class OrderAdpater(val context:Context): RecyclerView.Adapter<OrderAdpater.ViewHolder>() {
     var orderlist : ArrayList<Order>?=null
     override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
          var view=LayoutInflater.from(context).inflate(R.layout.row_item,p0,false)
          return ViewHolder(view)
     }

     override fun getItemCount(): Int {
          return orderlist?.size?:0
     }

     override fun onBindViewHolder(viewhoilder: ViewHolder, position: Int) {
          viewhoilder!!.name.setText(orderlist!!.get(position).name)
          viewhoilder!!.qty.setText(orderlist!!.get(position).qty.toString())
     }

     fun setItems( orderlist:ArrayList<Order>){
          this.orderlist=orderlist
          notifyDataSetChanged()
     }
     fun removeItem(position: Int) : Order{
          val remove = orderlist!!.removeAt(position)
          notifyItemRangeRemoved(position,itemCount-1)
          return remove
     }
    class ViewHolder(var itemv: View):RecyclerView.ViewHolder(itemv) {
         var name=itemv.findViewById<TextView>(R.id.itemName)
         var qty=itemv.findViewById<TextView>(R.id.itemQty)

     }

}