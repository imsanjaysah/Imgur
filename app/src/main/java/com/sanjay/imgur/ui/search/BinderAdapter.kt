package com.sanjay.imgur.ui.search

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sanjay.imgur.R
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun loadImage(img: ImageView, imageUrl: String?) {
    Picasso.get()
        .load("https://i.imgur.com/$imageUrl.png")
        .placeholder(R.drawable.ic_placeholder)
        .into(img)
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}