package com.example.ayurved_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListing_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val cart: MutableList<Product> = mutableListOf()

    private val cartUpdateLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val updatedCart = result.data?.getParcelableArrayListExtra<Product>("updated_cart_items")
            updatedCart?.let {
                cart.clear()
                cart.addAll(it)
                Toast.makeText(this, "Cart updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlisting)

        recyclerView = findViewById(R.id.recyclerView)

        val products = listOf(
            Product(1, "Product 1", 10.0, R.drawable.pills),
            Product(2, "Product 2", 20.0, R.drawable.medicine),
            Product(3, "Product 3", 40.0, R.drawable.pill2),
            Product(4, "Product 4", 20.0, R.drawable.tablet),
            Product(5, "Product 5", 10.0, R.drawable.pill3),
            Product(6, "Product 6", 20.0, R.drawable.pills),
            Product(7, "Product 7", 100.0, R.drawable.medicine),
            Product(8, "Product 8", 200.0, R.drawable.pill2),
            Product(9, "Product 9", 100.0, R.drawable.tablet),
            Product(10, "Product 10", 20.0, R.drawable.pill3)

        )

        adapter = ProductAdapter(
            products,
            onAddToCartClick = { product ->
                cart.add(product)
                Toast.makeText(this, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
            },
            onQuantityChanged = { product ->
                val index = cart.indexOfFirst { it.id == product.id }
                if (index >= 0) {
                    cart[index].quantity = product.quantity
                }
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val cartButton: ImageView = findViewById(R.id.cartButton)
        cartButton.setOnClickListener {

            val intent = Intent(this, CartScreen_Activity::class.java)
            intent.putParcelableArrayListExtra("cart_items", ArrayList(cart))
            cartUpdateLauncher.launch(intent)
        }
    }
}
