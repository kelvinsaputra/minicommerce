package com.radioandactive.minicommerce

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import com.radioandactive.minicommerce.cart.CartActivity
import com.radioandactive.minicommerce.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        doAsync {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            //db.productDao().insertAll(ProductFromDatabase(null, "Socks", 1.99))
            val prds = db.productDao().getAll()

            uiThread {
                d("kelvin", "product size? ${prds.size}")
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, MainFragment())
            .commit()

        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, MainFragment())
                        .commit()
                }
                R.id.action_jeans -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, JeansFragment())
                        .commit()
                    d("jeans", "jeans was pressed")
                }
                R.id.action_shorts -> d("jeans", "shorts was pressed")
                R.id.action_admin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, AdminFragment())
                        .commit()
                }
            }
            it.isChecked = true
            drawer_layout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.action_cart) {
            startActivity(Intent(this, CartActivity::class.java))
            return true
        }
        drawer_layout.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
