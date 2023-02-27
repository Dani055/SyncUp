package com.example.syncup_android.data

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