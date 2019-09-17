package com.radioandactive.minicommerce

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.radioandactive.minicommerce.database.AppDatabase
import com.radioandactive.minicommerce.database.ProductFromDatabase
import kotlinx.android.synthetic.main.fragment_admin.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdminFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener {
            val title = edt_product_title.text

            doAsync {
                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()

                db.productDao().insertAll(ProductFromDatabase(null, title.toString(), 1.99))
                val prds = db.productDao().getAll()

                uiThread {
                    d("kelvin", "AHAHA")
                }
            }
        }
    }
}