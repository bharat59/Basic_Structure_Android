package com.example.basicstructure.util

import android.view.View
import java.util.*

/**
 * Created by Bharat.
 */



fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.firstLetterCap() : String = this.substring(0, 1).uppercase(Locale.ROOT) + this.substring(1)
    .lowercase(
        Locale.ROOT
    )