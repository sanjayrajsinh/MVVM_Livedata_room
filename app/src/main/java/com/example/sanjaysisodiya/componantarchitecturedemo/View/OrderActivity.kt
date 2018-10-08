package com.example.sanjaysisodiya.componantarchitecturedemo.View

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sanjaysisodiya.componantarchitecturedemo.Model.Order
import com.example.sanjaysisodiya.componantarchitecturedemo.R
import com.example.sanjaysisodiya.componantarchitecturedemo.ViewModel.OrderViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class OrderActivity : AppCompatActivity() {

     var count = 20
     var mViewModel: OrderViewModel? = null
     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)
          recyclerview.layoutManager = LinearLayoutManager(this);

          // set animation
          var animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_fall_down)
          recyclerview.layoutAnimation = animation

          //adapter
          var adapter = OrderAdpater(this)
          recyclerview.adapter = adapter

          //set viewmodel class for abserving
          mViewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
          mViewModel!!.getOrderLiveData()!!.observe(this, Observer<List<Order>>() { list: List<Order>? ->
               adapter.setItems(list as ArrayList<Order>)
          })


          removeOrder(adapter)

          addOrder(animation)

     }

     private fun removeOrder(adapter: OrderAdpater) {
          // swipe interface implement for delete row
          var swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
               override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
               }

               override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val removeItem = adapter.removeItem(viewHolder.adapterPosition)
                    mViewModel!!.delete(removeItem)
               }

          }
          ItemTouchHelper(swipe).attachToRecyclerView(recyclerview)
     }

     private fun addOrder(animation: LayoutAnimationController) {
          floatingActionButton.setOnClickListener {
               var order: Order? = Order()
               order?.name = "Sanjay  " + count
               order?.qty = 5 + count
               count++
               mViewModel!!.insert(order!!)
               recyclerview.layoutAnimation = animation
               recyclerview.scheduleLayoutAnimation()
          }
     }

}
