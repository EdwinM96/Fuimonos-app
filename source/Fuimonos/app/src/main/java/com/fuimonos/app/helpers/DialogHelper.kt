package com.fuimonos.app.helpers

import android.app.Activity
import android.graphics.Typeface
import android.view.ViewGroup
import android.view.WindowManager
import com.fuimonos.app.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_single_option.view.*

object DialogHelper {

    fun showSingleOptionDialog(activity: Activity, titleRes: Int, iconRes: Int, mainDescriptionRes: Int,
                               secondaryDescriptionRes: Int, actionTextRes: Int = R.string.come_back,
                               action: (() -> Unit)? = null) {

        val builder  = MaterialAlertDialogBuilder(activity)

        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_single_option, null)

        view.tvTitle.setText(titleRes)
        view.ivIcon.setImageResource(iconRes)
        view.tvMainDescription.setText(mainDescriptionRes)
        view.tvSecondaryDescription.setText(secondaryDescriptionRes)
        view.btnAction.setText(actionTextRes)

        builder.setView(view)

        val dialog = builder.create()

        view.btnAction.setOnClickListener {
            action?.invoke()
            dialog.dismiss()
        }

        dialog.show()

    }

}
