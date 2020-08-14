package com.fuimonos.app.signup

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.fuimonos.app.R
import com.fuimonos.app.commons.BindableVMActivity
import com.fuimonos.app.commons.picasso.CircleTransform
import com.fuimonos.app.databinding.ActSignupBinding
import com.fuimonos.app.signup.SignUpValidation.*
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK) { return }
        data ?: return
        if(requestCode == PROFILE_PHOTO_REQ_CODE) {
            handleProfilePhotoFile(data)
        }
    }

    override fun setupSubscription() {
        super.setupSubscription()
        mViewModel.onClearValidations.observe(this, Observer {
            clearValidations()
        })
        mViewModel.onShowValidations.observe(this, Observer { validations ->
            showValidations(validations)
        })
        mViewModel.onPickProfilePhoto.observe(this, Observer {
            pickProfilePhoto()
        })
    }

    private fun pickProfilePhoto() {
        ImagePicker.with(this)
            .cropSquare()
            .galleryOnly()
            .galleryMimeTypes(arrayOf(
                "image/png",
                "image/jpg",
                "image/jpeg"
            ))
            .maxResultSize(512,512)
            .start(PROFILE_PHOTO_REQ_CODE)
    }

    private fun handleProfilePhotoFile(data: Intent) {
        val profilePhotoFile = ImagePicker.getFile(data)
        mViewModel.profilePhotoFile = profilePhotoFile
        profilePhotoFile ?: return
        Picasso.get()
            .load(profilePhotoFile)
            .transform(CircleTransform())
            .into(ivProfilePhoto)
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

    companion object {
        private const val PROFILE_PHOTO_REQ_CODE = 1
    }

}
