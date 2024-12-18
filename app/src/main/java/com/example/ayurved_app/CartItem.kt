package com.example.ayurved_app

import android.os.Parcel
import android.os.Parcelable


data class CartItem(
    val product: Product,
    var quantity: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Product::class.java.classLoader)!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(product, flags)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem = CartItem(parcel)
        override fun newArray(size: Int): Array<CartItem?> = arrayOfNulls(size)
    }
}
