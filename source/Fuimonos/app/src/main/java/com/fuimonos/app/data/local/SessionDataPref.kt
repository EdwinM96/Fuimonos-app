package com.fuimonos.app.data.local

import com.fuimonos.app.data.local.sharedprefs.IPrefManager

class SessionDataPref(private val prefManager: IPrefManager) {

    fun saveCredentials(credentials: String) {
        prefManager.putString(CREDENTIALS_KEY, credentials)
    }

    fun getCredentials(): String? {
        return prefManager.getString(CREDENTIALS_KEY)
    }

    fun saveProfilePhoto(profilePhoto: String) {
        prefManager.putString(PROFILE_PHOTO_KEY, profilePhoto)
    }

    fun getProfilePhoto(): String? {
        return prefManager.getString(PROFILE_PHOTO_KEY)
    }

    fun saveNameLogged(name: String) {
        prefManager.putString(USER_NAME_KEY, name)
    }

    fun getNameLogged(): String? {
        return prefManager.getString(USER_NAME_KEY)
    }

    fun saveUserEmail(email: String) {
        prefManager.putString(USER_EMAIL_KEY, email)
    }

    fun getUserEmail(): String? {
        return prefManager.getString(USER_EMAIL_KEY)
    }

    fun existsSessionData(): Boolean {
        return prefManager.contains(CREDENTIALS_KEY)
    }

    fun clearSessionData() {
        with(prefManager) {
            removeKey(CREDENTIALS_KEY)
            removeKey(USER_NAME_KEY)
            removeKey(PROFILE_PHOTO_KEY)
            removeKey(USER_EMAIL_KEY)
        }

    }

    companion object {
        private const val CREDENTIALS_KEY = "CREDENTIALS"
        private const val USER_NAME_KEY = "USER_NAME"
        private const val PROFILE_PHOTO_KEY = "PROFILE_PHOTO"
        private const val USER_EMAIL_KEY = "USER_EMAIL"
    }

}
