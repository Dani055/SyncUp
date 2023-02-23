package com.example.syncup_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.syncup_android.state.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow();

    fun changeUsername(email: String){
        _uiState.update { currentState->
            currentState.copy(email = email)
        }
    }
    fun changePassword(pass: String){
        _uiState.update { currentState->
            currentState.copy(password = pass)
        }
    }
    fun changeRememberMe(){
        _uiState.update { currentState->
            currentState.copy(rememberMe = !currentState.rememberMe)
        }
    }
}