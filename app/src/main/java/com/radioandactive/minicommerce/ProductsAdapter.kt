package com.radioandactive.minicommerce

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.radioandactive.minicommerce.ProductsAdapter.ViewHolder
import com.radioandactive.minicommerce.model.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(private val products: List<Product>) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener{
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("title", products[holder.adapterPosition].title)
            intent.putExtra("photo_url", products[holder.adapterPosition].photoUrl)
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString()
        if(product.isOnSale) {
            holder.imageSale.visibility = View.VISIBLE
        } else {
            holder.imageSale.visibility = View.GONE
        }
    }

    class ViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.iv_photo)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val imageSale : ImageView = itemView.findViewById(R.id.iv_sale)
    }

}