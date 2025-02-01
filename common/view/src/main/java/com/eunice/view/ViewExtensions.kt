package com.eunice.view

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by {EUNICE BAKARE T.} on {6/5/23}
 * Email: {eunice@reach.africa}
 */

fun ImageView.loadImage(img: String) = Glide
        .with(this)
        .load(img)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)

fun ImageView.imageWithPlaceholder(img: String, placeholder: Int) =
    Glide.with(this)
        .load(img)
        .placeholder(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)