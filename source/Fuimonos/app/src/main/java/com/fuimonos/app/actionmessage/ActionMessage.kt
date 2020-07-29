package com.fuimonos.app.actionmessage

import android.app.Activity
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActionMessage(val title: String,
                         val icon: Int,
                         val subtitle: String,
                         val message: String,
                         val actionButtonText: String,
                         val isBackEnabled: Boolean = false,
                         val clearBackStack: Boolean = false) : Parcelable
