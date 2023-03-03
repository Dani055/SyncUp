package com.example.syncup_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.syncup_android.api.repository.BingoRepository
import com.example.syncup_android.data.model.Activity
import com.example.syncup_android.state.ActivityDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ActivityDetailsViewModel(private val bingoRepo: BingoRepository = BingoRepository()): ViewModel() {
    private val _uiState = MutableStateFlow(ActivityDetailsState())
    val uiState: StateFlow<ActivityDetailsState> = _uiState.asStateFlow();

    suspend fun loadActivityDetails(activityId: String) {
        val activityRes = bingoRepo.getActivityById(activityId)
        _uiState.update { currentState->
            currentState.copy(activity = activityRes.activity, isCompleted = activityRes.isCompleted)
        }
    }
}