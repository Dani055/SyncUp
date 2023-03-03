package com.example.syncup_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.syncup_android.api.repository.AuthRepository
import com.example.syncup_android.api.repository.BingoRepository
import com.example.syncup_android.data.model.Activity
import com.example.syncup_android.data.model.Submission
import com.example.syncup_android.data.res.SignInResponse
import com.example.syncup_android.state.LoginUIState
import com.example.syncup_android.state.PlayBingoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlayBingoViewModel(private val bingoRepo: BingoRepository = BingoRepository()): ViewModel() {
    private val _uiState = MutableStateFlow(PlayBingoState())
    val uiState: StateFlow<PlayBingoState> = _uiState.asStateFlow();

    suspend fun loadTasks() {
        val activities: List<Activity> = bingoRepo.getActivities().activities
        _uiState.update { currentState->
            currentState.copy(activities = activities)
        }
    }
    suspend fun loadSubmissions() {
        val submissions: List<Submission> = bingoRepo.getSubmissionsForBingo().submissions
        _uiState.update { currentState->
            currentState.copy(submissions = submissions)
        }
    }
}