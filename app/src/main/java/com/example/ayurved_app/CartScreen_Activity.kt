package com.example.ayurved_app


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartScreen_Activity : AppCompatActivity() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var totalItemsTextView: TextView
    private lateinit var totalPriceTextView: TextView
    private var cartItems: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_screen)


        cartItems = intent.getParcelableArrayListExtra("cart_items") ?: mutableListOf()


        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)


        totalItemsTextView = findViewById(R.id.totalItemsTextView)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)


        val adapter = CartAdapter(cartItems) { updatedProduct ->

            updateCartData()
        }
        cartRecyclerView.adapter = adapter


        updateCartData()
    }

    private fun updateCartData() {

        val totalItems = cartItems.sumOf { it.quantity }
        val totalPrice = cartItems.sumOf { it.price * it.quantity }

        totalItemsTextView.text = "Total Items: $totalItems"
        totalPriceTextView.text = "Total Price: ${"%.2f Rs".format(totalPrice)}"
    }
}
