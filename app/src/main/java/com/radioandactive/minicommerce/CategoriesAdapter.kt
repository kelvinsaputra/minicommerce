package com.radioandactive.minicommerce

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.category_row.view.*

class CategoriesAdapter(private val categories: List<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.category_row, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.categoryName.text = categories[p1]
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val categoryName : TextView = view.tv_category
    }

}
