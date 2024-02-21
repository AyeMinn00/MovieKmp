package com.amo.moviekmp.android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amo.moviekmp.domain.login.LoginFormState
import com.amo.moviekmp.domain.login.doesInputValidate
import com.amo.moviekmp.domain.login.updateEmail
import com.amo.moviekmp.domain.login.updatePassword
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var _formState = MutableStateFlow(LoginFormState())
    val formState = _formState.asStateFlow()

    private val _screenState = MutableStateFlow<LoginScreenState>(LoginScreenState.Normal)
    val screenState = _screenState.asStateFlow()

    fun login() {
        val (form, validate) = _formState.value.doesInputValidate()
        if (!validate) {
            _formState.update { form }
            return
        }
        viewModelScope.launch {
            _screenState.update {
                LoginScreenState.Loading
            }
            delay(5_000)
            _screenState.update {
                LoginScreenState.Success("Login Success")
            }
        }
    }

    fun updateEmail(email: String) {
        _formState.update {
            it.updateEmail(email)
        }
    }

    fun updatePassword(pwd: String) {
        _formState.update {
            it.updatePassword(pwd)
        }
    }

}


sealed class LoginScreenState {
    data object Loading : LoginScreenState()
    data class Success(val msg: String?) : LoginScreenState()
    data class Failure(val msg: String?) : LoginScreenState()
    data object Normal : LoginScreenState()
}