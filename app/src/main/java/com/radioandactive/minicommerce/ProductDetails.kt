package com.radioandactive.minicommerce

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        val photoUrl = intent.getStringExtra("photo_url")

        tv_product_title.text = title
        Picasso.get().load(photoUrl).into(iv_product_photo)

        btn_availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey, $title is in stock!")
                //.setPositiveButton("Ok", object : DialogInterface.OnClickListener{
                //    override fun onClick(dialog: DialogInterface?, which: Int) {

                //    }

                //}) Messy form
                // Good form
                .setPositiveButton("Ok") { dialog, which -> Toast.makeText(applicationContext,"Very Nice",Toast.LENGTH_SHORT).show()}
                .create()
                .show()
        }
    }
}