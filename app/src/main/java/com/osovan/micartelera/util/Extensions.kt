package com.osovan.micartelera.util

import android.widget.ImageView
import com.bumptech.glide.Glide

//cargar URL con Glide  --> load("www.test.com")
fun ImageView.load(url: String) {
     if (url.isNotEmpty()) {
          Glide.with(this.context).load(url).into(this)
     }
}