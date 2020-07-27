package com.fuimonos.app.data.local.sharedprefs

import android.content.Context
import com.pddstudio.preferences.encrypted.EncryptedPreferences

class CryptedPrefManager(context: Context) : IPrefManager {

    private val cryptedSharedPref = EncryptedPreferences.Builder(context)
        .withPreferenceName(ENCRYPTED_PREF_NAME)
        .withEncryptionPassword(ENCRYPTION_PASSWORD_PREF)
        .withSaveAsSingleton(true)
        .build()

    private val editor = cryptedSharedPref.edit()

    override fun putString(key: String, value: String?) {
        editor.putString(key, value).apply()
    }

    override fun getString(key: String, defValue: String?): String? {
        return cryptedSharedPref.getString(key, defValue)
    }

    override fun removeKey(key: String) {
        editor.remove(key)
    }

    companion object {
        const val ENCRYPTED_PREF_NAME = "VAULT_PREF"
        const val ENCRYPTION_PASSWORD_PREF = "fu1m0no5" //TODO: OBFUSCAR
    }

}
