package com.fuimonos.app.userprofile

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.data.local.SessionDataPref

class UserProfileViewModel(private val sessionDataPref: SessionDataPref) : BaseViewModel() {

    val userFirstName = MutableLiveData(getFirstName())
    val userEmail = MutableLiveData(getUserEmail())

    val onShowProfilePhoto = MutableLiveData<String>()
    val onChangePassword = SingleLiveEvent<Nothing>()
    val onLogout = SingleLiveEvent<Nothing>()

    fun start() {
        onShowProfilePhoto.value = sessionDataPref.getProfilePhoto()
    }

    fun onChangePassword() {
        onChangePassword.call()
    }

    fun onRecoverPassword() {
        toast.value = "No disponible"
    }

    fun onLogout() {
        sessionDataPref.clearSessionData()
        onLogout.call()
    }

    private fun getFirstName(): String {
        val nameLogged = sessionDataPref.getNameLogged() ?: ""
        val names = nameLogged.split(" ")
        return names.first()
    }

    private fun getUserEmail(): String {
        return sessionDataPref.getUserEmail() ?: ""
    }

}