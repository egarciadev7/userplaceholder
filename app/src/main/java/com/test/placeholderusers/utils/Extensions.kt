package com.test.placeholderusers.utils

import android.view.View

fun View.hidden() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.show(show: Boolean) {
    if (show)
        this.show()
    else
        this.hidden()
}
