package com.example.ayurved_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductDetail_Activity : AppCompatActivity() {

    private lateinit var productImage: ImageView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView

    private lateinit var quantityText: TextView

    private var product: Product? = null
    private var quantity = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        productImage = findViewById(R.id.productImage)
        productName = findViewById(R.id.productName)
        productPrice = findViewById(R.id.productPrice)
        productDescription = findViewById(R.id.productDescription)



        product = intent.getParcelableExtra("product")
        product?.let {
            displayProductDetails(it)
        }



        val cartButton: ImageView = findViewById(R.id.cartButton)
        cartButton.setOnClickListener {
            val intent = Intent(this, CartScreen_Activity::class.java)
            startActivity(intent)

        }
    }

    private fun displayProductDetails(product: Product) {
        productImage.setImageResource(product.imageResId)
        productName.text = product.name
        productPrice.text = "Rs.${product.price}"
        productDescription.text = "This is a detailed description of ${product.name}."



    }
}