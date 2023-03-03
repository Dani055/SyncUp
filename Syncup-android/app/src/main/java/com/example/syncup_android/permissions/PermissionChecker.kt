package com.example.syncup_android.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

class PermissionChecker : IPermissionChecker{
    override fun checkMultiplePermissions(context: Context, permissions: Array<String>) : Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}

@Composable
fun CheckAndRequestLocationPermission() {
    val context = LocalContext.current
    val pc : IPermissionChecker = PermissionChecker();
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    if(!pc.checkMultiplePermissions(context = context, permissions = permissions)){
        //Either display message to go allow permissions or request them
        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        LaunchedEffect(Unit){
            launcherMultiplePermissions.launch(permissions)
        }
    }

}