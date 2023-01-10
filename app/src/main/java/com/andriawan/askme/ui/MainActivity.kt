package com.andriawan.askme.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.andriawan.askme.navigation.AskMeNavigation
import com.andriawan.askme.ui.components.AskMeBottomNavigation
import com.andriawan.askme.ui.themes.AskMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskMeTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    content = {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colors.background
                        ) {
                            AskMeNavigation(
                                navController = navController,
                                snackBarHostState = scaffoldState.snackbarHostState
                            )
                        }
                    },
                    scaffoldState = scaffoldState,
                    bottomBar = {
                        AskMeBottomNavigation()
                    }
                )
            }
        }
    }
}