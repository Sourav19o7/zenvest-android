package com.zenvest.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zenvest.core.common.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun isLoggedIn(): Boolean = sessionManager.isLoggedIn()

    fun isNewUser(): Boolean = sessionManager.isNewUser()

    fun hasCompletedConsent(): Boolean = sessionManager.hasCompletedConsent()
}
