package com.example.syncup_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncup_android.api.AuthRepository
import com.example.syncup_android.data.req.SignInRequest
import com.example.syncup_android.data.res.SignInResponse
import com.example.syncup_android.state.LoginUIState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import org.json.JSONObject
import retrofit2.Response
import java.net.ResponseCache

class LoginViewModel(
    private val authRepo: AuthRepository = AuthRepository()
): ViewModel() {
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
    suspend fun login(): SignInResponse{
        return authRepo.login(_uiState.value.email)
    }
}