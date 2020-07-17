package com.fuimonos.app.ext

import androidx.core.util.PatternsCompat

fun String?.isValidEmail(): Boolean {
    if(this.isNullOrBlank()) { return false }
    return PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}
