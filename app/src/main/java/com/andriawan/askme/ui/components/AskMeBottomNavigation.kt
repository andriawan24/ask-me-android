package com.andriawan.askme.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.andriawan.askme.ui.themes.AskMeTheme

@Composable
fun AskMeBottomNavigation() {
    BottomNavigation(
        backgroundColor = if (!isSystemInDarkTheme()) Color.White else MaterialTheme.colors.background
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onBackground,
            alwaysShowLabel = false,
            label = {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp
                    )
                )
            }
        )

        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.QuestionMark,
                    contentDescription = "Home"
                )
            },
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onBackground,
            alwaysShowLabel = false,
            label = {
                Text(text = "Home")
            }
        )

        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.QuestionAnswer,
                    contentDescription = "Home"
                )
            },
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onBackground,
            alwaysShowLabel = false,
            label = {
                Text(text = "Home")
            }
        )

        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person4,
                    contentDescription = "Home"
                )
            },
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onBackground,
            alwaysShowLabel = false,
            label = {
                Text(text = "Home")
            }
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AskMeBottomNavigationPreview() {
    AskMeTheme {
        AskMeBottomNavigation()
    }
}