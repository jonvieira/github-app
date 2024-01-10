package com.jonas.vieira.githubapp.ui.core

import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

fun ImageView.setImage(avatarUrl: String) {
    Picasso.get()
        .load(avatarUrl)
        .transform(CropCircleTransformation())
        .into(this)
}