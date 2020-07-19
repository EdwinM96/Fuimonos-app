package com.fuimonos.app.signup

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.databinding.ActSignupBinding
import com.fuimonos.app.signup.SignUpValidation.*
import kotlinx.android.synthetic.main.act_signup.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : BindableVMActivity<SignUpViewModel, ActSignupBinding>() {

    override val mViewModel: SignUpViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.act_signup
    private val mBirthdayCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    override fun setupSubscription() {
        super.setupSubscription()
        mViewModel.onClearValidations.observe(this, Observer {
            clearValidations()
        })
        mViewModel.onShowValidations.observe(this, Observer { validations ->
            showValidations(validations)
        })
    }

    private fun setup() {
        setupBirthdayField()
    }

    private fun setupBirthdayField() {
        etBirthday.keyListener = null
        etBirthday.setOnClickListener {

            val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                mBirthdayCalendar.set(Calendar.YEAR, year)
                mBirthdayCalendar.set(Calendar.MONTH, monthOfYear)
                mBirthdayCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val simpleDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
                val formattedDate = simpleDateFormat.format(mBirthdayCalendar.time)
                etBirthday.setText(formattedDate)
            }

            DatePickerDialog(this@SignUpActivity,
                             onDateSetListener,
                             mBirthdayCalendar.get(Calendar.YEAR),
                             mBirthdayCalendar.get(Calendar.MONTH),
                             mBirthdayCalendar.get(Calendar.DAY_OF_MONTH)).show()

        }
    }

    private fun clearValidations() {
        inputName.error = null
        inputLastName.error = null
        inputEmail.error = null
        inputPassword.error = null
        inputBirthday.error = null
        inputPhoneNumber.error = null
    }

    private fun showValidations(validations: List<SignUpValidation>) {
        validations.forEach { validation ->
            when(validation) {
                EMTPY_NAME         -> inputName.error = getString(R.string.val_empty_name)
                EMTPY_LAST_NAME    -> inputLastName.error = getString(R.string.val_emtpy_last_name)
                EMPTY_EMAIL        -> inputEmail.error = getString(R.string.val_empty_email)
                INVALID_EMAIL      -> inputEmail.error = getString(R.string.val_invalid_email)
                EMTPY_PASSWORD     -> inputPassword.error = getString(R.string.val_empty_password)
                EMPTY_PHONE_NUMBER -> inputPhoneNumber.error = getString(R.string.val_empty_phone_number)
            }
        }
    }

}
