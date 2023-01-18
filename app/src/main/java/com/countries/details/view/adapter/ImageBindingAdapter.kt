package com.countries.details.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
class ImageBindingAdapter {
companion object {
    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}
}