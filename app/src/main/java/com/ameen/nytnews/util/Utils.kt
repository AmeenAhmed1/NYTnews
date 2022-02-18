package com.ameen.nytnews.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


fun Context.loadImageUrl(imageUrl: String?, imageView: ImageView?) =
    imageView?.let {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(it)
    }
