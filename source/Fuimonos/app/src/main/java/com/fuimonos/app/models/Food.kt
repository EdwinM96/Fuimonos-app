package com.fuimonos.app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(val id: Int?,
                val name: String?,
                val description: String?,
                val price: Float?,
                val image: String?) : Parcelable
