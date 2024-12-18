package com.example.ayurved_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartItems: MutableList<Product>,
    private val onQuantityChanged: (Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.bind(product)
        holder.cartProductImage.setImageResource(product.imageResId)
    }

    override fun getItemCount(): Int = cartItems.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.cartProductName)
        private val productPrice: TextView = itemView.findViewById(R.id.cartProductPrice)
        private val quantityText: TextView = itemView.findViewById(R.id.cartProductQuantity)
        private val incrementButton: Button = itemView.findViewById(R.id.incrementButton)
        private val decrementButton: Button = itemView.findViewById(R.id.decrementButton)
        val cartProductImage: ImageView = itemView.findViewById(R.id.cartProductImage)

        fun bind(product: Product) {
            productName.text = product.name
            productPrice.text = "Rs.${product.price}"
            quantityText.text = product.quantity.toString()


            incrementButton.setOnClickListener {
                product.quantity++
                onQuantityChanged(product)
                notifyItemChanged(adapterPosition)
            }


            decrementButton.setOnClickListener {
                if (product.quantity > 1) {
                    product.quantity--
                    onQuantityChanged(product)
                    notifyItemChanged(adapterPosition)
                } else {
                    cartItems.removeAt(adapterPosition)
                    onQuantityChanged(product)
                    notifyItemRemoved(adapterPosition)
                }
            }
        }
    }
}



