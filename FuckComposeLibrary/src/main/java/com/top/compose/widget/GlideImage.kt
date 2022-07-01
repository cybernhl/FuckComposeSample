package com.top.compose.widget

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun loadPicture(url: String, placeholder: Painter?): Painter? {

    var state by remember {
        mutableStateOf(placeholder)
    }

    val options: RequestOptions
    val context = LocalContext.current
    val result = object : CustomTarget<Bitmap>() {
        override fun onLoadCleared(p: Drawable?) {
            state = placeholder
        }

        override fun onResourceReady(
            resource: Bitmap,
            transition: Transition<in Bitmap>?
        ) {
            state = BitmapPainter(resource.asImageBitmap())
        }
    }
    try {
        Glide.with(context)
            .asBitmap()
            .centerCrop()
            .load(url)
            .into(result)
    } catch (e: Exception) {

    }
    return state
}

@Composable
fun GlideImage(url: String, modifier: Modifier = Modifier) {
    val painter = loadPicture(
        url = url,
        placeholder = null
    )
    if (painter != null) {
        Image(painter = painter, contentDescription = "", modifier = modifier)
    }
}