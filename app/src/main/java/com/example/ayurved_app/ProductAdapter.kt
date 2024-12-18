package com.example.ayurved_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>,
    private val onAddToCartClick: (Product) -> Unit,
    private val onQuantityChanged: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
        val quantityLayout: View = itemView.findViewById(R.id.quantityLayout)
        val quantityText: TextView = itemView.findViewById(R.id.quantityText)
        val incrementButton: Button = itemView.findViewById(R.id.incrementButton)
        val decrementButton: Button = itemView.findViewById(R.id.decrementButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.productImage.setImageResource(product.imageResId)
        holder.productName.text = product.name
        holder.productPrice.text = "Rs.${product.price}"
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetail_Activity::class.java)
            intent.putExtra("product", product)
            holder.itemView.context.startActivity(intent)
        }


        if (product.quantity > 0) {
            holder.addToCartButton.visibility = View.GONE
            holder.quantityLayout.visibility = View.VISIBLE
            holder.quantityText.text = product.quantity.toString()
        } else {
            holder.addToCartButton.visibility = View.VISIBLE
            holder.quantityLayout.visibility = View.GONE
        }

        holder.addToCartButton.setOnClickListener {
            product.quantity = 1
            onAddToCartClick(product)
            notifyItemChanged(position)
        }

        holder.incrementButton.setOnClickListener {
            product.quantity++
            onQuantityChanged(product)
            notifyItemChanged(position)
        }

        holder.decrementButton.setOnClickListener {
            if (product.quantity > 1) {
                product.quantity--
            } else {
                product.quantity = 0
            }
            onQuantityChanged(product)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = productList.size
}
