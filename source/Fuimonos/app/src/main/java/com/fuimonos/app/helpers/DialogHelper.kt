package com.fuimonos.app.helpers

import android.app.Activity
import com.fuimonos.app.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_single_option.view.*

object DialogHelper {

    data class SingleOptionAttr(var titleRes: Int = 0,
                                var iconRes: Int = 0,
                                var mainDescriptionRes: Int = 0,
                                var secondaryDescriptionRes: Int = 0,
                                var actionTextRes: Int = R.string.come_back,
                                var action: (() -> Unit)? = null)

    fun showSingleOptionDialog(activity: Activity, attr: SingleOptionAttr) {
        showSingleOptionDialog(activity) {
            titleRes = attr.titleRes
            iconRes = attr.iconRes
            mainDescriptionRes = attr.mainDescriptionRes
            secondaryDescriptionRes = attr.secondaryDescriptionRes
            actionTextRes = attr.actionTextRes
            action = attr.action
        }
    }

    fun showSingleOptionDialog(activity: Activity, attr: SingleOptionAttr.() -> Unit) {

        val singleOptionAttr = SingleOptionAttr()
        singleOptionAttr.apply(attr)

        val builder  = MaterialAlertDialogBuilder(activity)

        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_single_option, null)

        with(singleOptionAttr) {
            view.tvTitle.setText(titleRes)
            view.ivIcon.setImageResource(iconRes)
            view.tvMainDescription.setText(mainDescriptionRes)
            view.tvSecondaryDescription.setText(secondaryDescriptionRes)
            view.btnAction.setText(actionTextRes)
        }

        builder.setView(view)

        val dialog = builder.create()

        view.btnAction.setOnClickListener {
            singleOptionAttr.action?.invoke()
            dialog.dismiss()
        }

        dialog.show()

    }

}
