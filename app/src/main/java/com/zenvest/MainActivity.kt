package com.zenvest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zenvest.core.ui.theme.ZenvestTheme
import com.zenvest.ui.navigation.ZenvestNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ZenvestTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    ZenvestNavGraph(
                        navController = navController,
                        onGoogleSignIn = {
                            // TODO: Implement Google Sign In
                            // Use Credential Manager API for Google Sign In
                        }
                    )
                }
            }
        }
    }
}
