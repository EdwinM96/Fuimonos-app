package com.fuimonos.app.commons

import com.fuimonos.app.R
import com.fuimonos.app.commons.exceptions.NoInternetException
import com.fuimonos.app.helpers.DialogHelper
import java.lang.Exception

object ErrorDialogDataBuilder {

    fun buildFrom(exception: Exception): DialogHelper.SingleOptionAttr {
        return when(exception) {
            is NoInternetException -> createNoInternetDialogData()
            else -> createUnknownErrorDialogData()
        }
    }

    private fun createNoInternetDialogData() : DialogHelper.SingleOptionAttr {
        return DialogHelper.SingleOptionAttr(
            R.string.no_internet_dialog_title,
            R.drawable.ic_no_internet,
            R.string.no_internet_dialog_main_description,
            R.string.no_internet_dialog_secondary_description
        )
    }

    private fun createUnknownErrorDialogData() : DialogHelper.SingleOptionAttr {
        return DialogHelper.SingleOptionAttr(
            R.string.unknown_error_dialog_title,
            R.drawable.ic_confused_person,
            R.string.unknown_error_dialog_mainDescription,
            R.string.unknown_error_dialog_secondaryDescription
        )
    }

}
