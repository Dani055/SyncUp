package com.example.syncup_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.syncup_android.api.repository.BingoRepository
import com.example.syncup_android.state.ProfileUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(private val bingoRepo: BingoRepository = BingoRepository()): ViewModel()  {
    private val _uiState = MutableStateFlow(ProfileUIState())
    val uiState: StateFlow<ProfileUIState> = _uiState.asStateFlow();

    suspend fun loadMySubmissions() {
        val res = bingoRepo.getSubmissionsForLoggedUser()
        _uiState.update { currentState->
            currentState.copy(mySubmissions = res.submissions)
        }
    }
}