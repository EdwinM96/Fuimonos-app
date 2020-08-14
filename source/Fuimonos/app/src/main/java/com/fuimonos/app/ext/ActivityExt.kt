package com.fuimonos.app.ext

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Activity.enableTouch() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.disableTouch() {
    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let {
        view.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

inline fun <reified T> Activity.argFromExtraLazy(key: String) = lazy {
    val extras = intent.extras ?: error("Intent has not extras")
    if(!extras.containsKey(key)) { error("Intent extra argument $key is missing") }
    val value = extras[key]
    if(value !is T) { error("Extra type not match with expected") }
    value
}

inline fun <reified T> Activity.argFromExtra(key: String): T {
    val extras = intent.extras ?: error("Intent has not extras")
    if(!extras.containsKey(key)) { error("Intent extra argument $key is missing") }
    val value = extras[key]
    if(value !is T) { error("Extra type not match with expected") }
    return value
}