package com.fuimonos.app.actionmessage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fuimonos.app.R
import com.fuimonos.app.databinding.ActActionMessageBinding
import com.fuimonos.app.ext.argFromExtra
import com.fuimonos.app.ext.argFromExtraLazy
import kotlinx.android.synthetic.main.dialog_single_option.*

class ActionMessageActivity : AppCompatActivity() {

    private lateinit var actionMessage: ActionMessage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        setupDatabinding()
        setupBtnAction()
    }

    private fun setupDatabinding() {
        val binding: ActActionMessageBinding = DataBindingUtil.setContentView(this, R.layout.act_action_message)
        actionMessage = argFromExtra(ACTION_MESSAGE_EXTRA)
        binding.actionMessage = actionMessage
    }

    private fun setupBtnAction() {
        btnAction.setOnClickListener {
            val intent = Intent(this, argFromExtra(DESTINATION_ACTIVITY_EXTRA))
            if(actionMessage.clearBackStack) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
        }
    }

    companion object {
        const val ACTION_MESSAGE_EXTRA = "ACTION_MESSAGE"
        const val DESTINATION_ACTIVITY_EXTRA = "DESTINATION_ACTIVITY"
        fun <T: Activity> launchWith(activity: Activity, actionMessage: ActionMessage, destinationActivity: Class<T>) {
            val intent = Intent(activity, this::class.java)
            intent.putExtra(ACTION_MESSAGE_EXTRA, actionMessage)
            intent.putExtra(DESTINATION_ACTIVITY_EXTRA, destinationActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
