package com.example.syncup_android

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.syncup_android.ui.navigation.MainApplicationScaffold
import com.example.syncup_android.ui.navigation.NavRoutes
import com.example.syncup_android.ui.theme.SyncupandroidTheme
import java.io.File

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            SyncupandroidTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SyncupApp()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SyncupApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val scope =  rememberCoroutineScope()

    val fullBackstackRoute = backStackEntry?.destination?.route ?: NavRoutes.Onboarding.name
    val indexOfSlash = fullBackstackRoute.indexOf("/")
    val backStackRoute = if(indexOfSlash >= 0) fullBackstackRoute.substring(0, indexOfSlash) else fullBackstackRoute
    val currentScreen = NavRoutes.valueOf(
        backStackRoute
    )

    MainApplicationScaffold(
        currentScreen = currentScreen,
        navController = navController,
        scope = scope,
    )
}

//File provider for jetpack compose to access the temporary images when creating a submission
class ComposeFileProvider : FileProvider(
    R.xml.filepaths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = context.packageName + ".fileprovider"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}