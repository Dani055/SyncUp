package com.example.syncup_android.data

import com.example.syncup_android.data.model.User

//Logged user in the application is saved here
class UserContext {
    companion object{
        var loggedUser: User? = null;

        fun login(user: User){
            loggedUser = user;
        }
        fun logout(){
            loggedUser = null;
        }
    }
}