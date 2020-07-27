package com.fuimonos.app.data.local.sharedprefs

interface IPrefManager {
    fun putString(key: String, value: String?)
    fun getString(key: String, defValue: String? = null): String?
    fun removeKey(key: String)
    fun contains(key: String): Boolean
}
