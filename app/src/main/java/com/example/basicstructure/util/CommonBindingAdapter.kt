package com.example.basicstructure.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

/**
 * Created by Bharat.
 */

@BindingAdapter("setImage")
fun setImage(imageView: AppCompatImageView,url : String?){
    url?.let {
        imageView.setImage(url)
    }
}