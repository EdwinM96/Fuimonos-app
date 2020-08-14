package com.fuimonos.app.ext

import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.getArg(key: String) = lazy {
    val args = arguments ?: error("Fragment has not arguments")
    if(!args.containsKey(key)) { error("$key is missing in fragment arguments") }
    val value = args[key]
    if(value !is T) { error("Argument does not match with ${T::class.simpleName}") }
    value
}
