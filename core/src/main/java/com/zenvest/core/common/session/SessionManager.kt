package com.zenvest.core.common.session

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private val encryptedPrefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveAuthToken(token: String) {
        encryptedPrefs.edit().putString(KEY_AUTH_TOKEN, token).apply()
    }

    fun getAuthToken(): String {
        return encryptedPrefs.getString(KEY_AUTH_TOKEN, "") ?: ""
    }

    fun saveEmail(email: String) {
        encryptedPrefs.edit().putString(KEY_EMAIL, email).apply()
    }

    fun getEmail(): String {
        return encryptedPrefs.getString(KEY_EMAIL, "") ?: ""
    }

    fun savePhone(phone: String) {
        encryptedPrefs.edit().putString(KEY_PHONE, phone).apply()
    }

    fun getPhone(): String {
        return encryptedPrefs.getString(KEY_PHONE, "") ?: ""
    }

    fun saveUserName(name: String) {
        encryptedPrefs.edit().putString(KEY_USER_NAME, name).apply()
    }

    fun getUserName(): String {
        return encryptedPrefs.getString(KEY_USER_NAME, "") ?: ""
    }

    fun saveIsNewUser(isNewUser: Boolean) {
        encryptedPrefs.edit().putBoolean(KEY_IS_NEW_USER, isNewUser).apply()
    }

    fun isNewUser(): Boolean {
        return encryptedPrefs.getBoolean(KEY_IS_NEW_USER, true)
    }

    fun saveHasCompletedOnboarding(completed: Boolean) {
        encryptedPrefs.edit().putBoolean(KEY_HAS_COMPLETED_ONBOARDING, completed).apply()
    }

    fun hasCompletedOnboarding(): Boolean {
        return encryptedPrefs.getBoolean(KEY_HAS_COMPLETED_ONBOARDING, false)
    }

    fun saveHasCompletedConsent(completed: Boolean) {
        encryptedPrefs.edit().putBoolean(KEY_HAS_COMPLETED_CONSENT, completed).apply()
    }

    fun hasCompletedConsent(): Boolean {
        return encryptedPrefs.getBoolean(KEY_HAS_COMPLETED_CONSENT, false)
    }

    fun isLoggedIn(): Boolean {
        return getAuthToken().isNotBlank()
    }

    fun clearSession() {
        encryptedPrefs.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "zenvest_secure_prefs"
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_IS_NEW_USER = "is_new_user"
        private const val KEY_HAS_COMPLETED_ONBOARDING = "has_completed_onboarding"
        private const val KEY_HAS_COMPLETED_CONSENT = "has_completed_consent"
    }
}
