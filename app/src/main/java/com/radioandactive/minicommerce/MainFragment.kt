package com.radioandactive.minicommerce

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.google.gson.Gson
import com.radioandactive.minicommerce.database.AppDatabase
import com.radioandactive.minicommerce.database.ProductFromDatabase
import com.radioandactive.minicommerce.model.Product
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_main.view.rv_categories
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val products = arrayListOf<Product>()

//        for(i in 0..10) {
//            products.add(Product("Organic Apple #$i", "https://via.placeholder.com/200/09f/fff.png", 1.99))
//        }

//        doAsync {
//            val json = URL("https://finepointmobile.com/data/products.json").readText()
//
//            uiThread {
////                d("jeans", "json:$json")
//                val products = Gson().fromJson(json, Array<Product>::class.java).toList()
//                root.recyclerview_main.apply {
////                                layoutManager = LinearLayoutManager(context)
//                    layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
//                    adapter = ProductsAdapter(products)
//                    root.progressBar.visibility = View.GONE
//                }
//            }
//        }

        root.progressBar.visibility = View.GONE

        doAsync {
            val db = Room.databaseBuilder(
                activity!!.applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            val productsFromDatabase = db.productDao().getAll()
            val products = productsFromDatabase.map {
                Product(it.title,
                    "https://finepointmobile.com/data/jeans3.jpg",
                    it.price,
                    "This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? ",
                    true)
            }

            uiThread {
                root.recyclerview_main.apply {
                    ////                                layoutManager = LinearLayoutManager(context)
                    layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products)
                    root.progressBar.visibility = View.GONE
                }
            }
        }

        val categories = listOf("Jeans", "Socks", "Suits", "Skirts", "Dresses", "Kelvin", "Pants", "Jackets", "Saputra")

        root.rv_categories.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                activity,
                androidx.recyclerview.widget.RecyclerView.HORIZONTAL,
                false
            )
            adapter = CategoriesAdapter(categories)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_search.setOnClickListener {

            doAsync {
                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()

                val productsFromDatabase = db.productDao().searchFor("%${search_term.text}%")
                val products = productsFromDatabase.map {
                    Product(it.title,
                        "https://finepointmobile.com/data/jeans3.jpg",
                        it.price,
                        "This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? This is a nice description. got it? ",
                        true)
                }

                uiThread {
                    recyclerview_main.apply {
                        layoutManager = GridLayoutManager(activity, 2)
                        adapter = ProductsAdapter(products)
                    }
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}