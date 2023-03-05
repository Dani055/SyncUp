package com.example.syncup_android.permissions

import android.content.Context

interface IPermissionChecker {
    fun checkMultiplePermissions(context: Context, permissions: Array<String>) : Boolean
}