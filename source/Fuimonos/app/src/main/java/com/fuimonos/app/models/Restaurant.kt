package com.fuimonos.app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(val id: Int?,
                      val outstandingImage: String?,
                      val logo: String?,
                      val name: String?,
                      val minimumMount: String?) : Parcelable
