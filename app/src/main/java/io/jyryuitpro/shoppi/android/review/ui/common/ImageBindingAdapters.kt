package io.jyryuitpro.shoppi.android.review.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import io.jyryuitpro.shoppi.android.review.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}