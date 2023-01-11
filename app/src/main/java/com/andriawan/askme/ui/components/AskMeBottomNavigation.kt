package com.andriawan.askme.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andriawan.askme.R
import com.andriawan.askme.navigation.Routes
import com.andriawan.askme.ui.themes.AskMeTheme

@Composable
fun AskMeBottomNavigation(
    currentRoutes: String,
    modifier: Modifier = Modifier
) {
    val bottomNavigationVisibleRoutes = listOf(
        Routes.HOME_PAGE
    )

    if (currentRoutes in bottomNavigationVisibleRoutes) {
        BottomNavigation(
            backgroundColor = if (!isSystemInDarkTheme()) Color.White else MaterialTheme.colors.background,
            modifier = modifier
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
        ) {
            BottomNavigationItem(
                selected = currentRoutes == Routes.HOME_PAGE,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home",
                        modifier = Modifier.size(28.dp)
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
                            fontSize = 12.sp
                        )
                    )
                },
            )

            BottomNavigationItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_questions),
                        contentDescription = "Question",
                        modifier = Modifier.size(28.dp)
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                alwaysShowLabel = false,
                label = {
                    Text(text = "Question")
                }
            )

            BottomNavigationItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_my_questions),
                        contentDescription = "My Questions",
                        modifier = Modifier.size(28.dp)
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                alwaysShowLabel = false,
                label = {
                    Text(text = "My Questions")
                }
            )

            BottomNavigationItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile",
                        modifier = Modifier.size(28.dp)
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                alwaysShowLabel = false,
                label = {
                    Text(text = "Profile")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AskMeBottomNavigationPreview() {
    AskMeTheme {
        AskMeBottomNavigation(
            currentRoutes = Routes.HOME_PAGE
        )
    }
}