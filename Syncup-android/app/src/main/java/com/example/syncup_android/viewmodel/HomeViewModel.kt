package com.example.syncup_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.syncup_android.api.repository.AuthRepository
import com.example.syncup_android.data.UserContext
import com.example.syncup_android.data.model.User
import com.example.syncup_android.state.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private val authRepo: AuthRepository = AuthRepository()
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow();

    suspend fun getLeaderboard() {
        val res = authRepo.getLeaderboard();
        val indexOfUser = res.sortedUsers.indexOfFirst { it.id == UserContext.loggedUser?.id ?: "none" }
        var relativeLeaderboard: HashMap<Int, User> = hashMapOf()
        if(indexOfUser >= 0){
            //You are first in the leaderboard
            if(indexOfUser == 0){
                relativeLeaderboard.put(1, res.sortedUsers[indexOfUser])
                var usersToAdd = res.sortedUsers.size - 1
                if(usersToAdd > 3){
                    usersToAdd = 3
                }
                if(usersToAdd > 0){
                    for (i in 1..usersToAdd){
                        relativeLeaderboard.put(i + 1, res.sortedUsers[i])
                    }
                }

            }
            //You are second
            else if(indexOfUser == 1){
                relativeLeaderboard.put(1, res.sortedUsers[indexOfUser - 1])
                relativeLeaderboard.put(2, res.sortedUsers[indexOfUser])
                var usersToAdd = res.sortedUsers.size - 2

                if(usersToAdd > 2){
                    usersToAdd = 2
                }
                if(usersToAdd > 0){
                    for (i in 1..usersToAdd){
                        relativeLeaderboard.put(indexOfUser + i + 1, res.sortedUsers[indexOfUser +i])
                    }
                }
            }
            //You are 3rd+, show 2 people ahead and 1 behind. If you are last show 3 people ahead
           else if(indexOfUser >= 2){
               //If you are last and there are more than 3 people ahead of you, add one more so the leaderboard fills up to 4
                if(res.sortedUsers.size == indexOfUser + 1 && indexOfUser >= 3){
                    relativeLeaderboard.put(indexOfUser - 2, res.sortedUsers[indexOfUser - 3])
                }
                relativeLeaderboard.put(indexOfUser - 1, res.sortedUsers[indexOfUser - 2])
                relativeLeaderboard.put(indexOfUser, res.sortedUsers[indexOfUser - 1])
                relativeLeaderboard.put(indexOfUser + 1, res.sortedUsers[indexOfUser])
                if(res.sortedUsers.size > indexOfUser + 1){
                    relativeLeaderboard.put(indexOfUser + 2, res.sortedUsers[indexOfUser + 1])
                }
            }

        }

        _uiState.update { currentState->
            currentState.copy(leaderboard = relativeLeaderboard, leaderboardSize = res.sortedUsers.size)
        }
    }
}