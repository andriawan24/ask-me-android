package com.andriawan.askme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.andriawan.askme.navigation.AskMeNavigation
import com.andriawan.askme.ui.themes.AskMeTheme
import com.andriawan.askme.utils.Constants.DEFAULT_MARGIN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AskMeTheme {
                val navController = rememberNavController()

                Scaffold(
                    content = {
                        Surface(
                            modifier = Modifier.padding(it),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            AskMeNavigation(navController = navController)
                        }
                    }
                )
            }
        }
    }
}