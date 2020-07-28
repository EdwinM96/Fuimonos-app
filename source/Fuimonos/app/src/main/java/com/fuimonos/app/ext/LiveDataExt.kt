package com.fuimonos.app.ext

import androidx.lifecycle.LiveData
import com.fuimonos.app.commons.Validation
import com.fuimonos.app.commons.Validator

fun <T, E> LiveData<T>.validate(init: Validation<T, E>.() -> Unit): List<E> {
    return Validator.validate(this, init)
}