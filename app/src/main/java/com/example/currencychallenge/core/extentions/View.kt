package com.example.currencychallenge.core.extentions

import android.view.View
import android.view.ViewGroup

fun View.show(): View {
    if (visibility != ViewGroup.VISIBLE) {
        visibility = ViewGroup.VISIBLE
    }
    return this
}

fun View.hide(invisible: Boolean = false): View {
    if (visibility != ViewGroup.GONE) {
        visibility = if (invisible) {
            ViewGroup.INVISIBLE
        } else {
            ViewGroup.GONE
        }
    }
    return this
}

fun View.enable(){
    if(!this.isEnabled) {
        isEnabled = true
    }
}

fun View.disable(){
    if(this.isEnabled) {
        isEnabled = false
    }
}