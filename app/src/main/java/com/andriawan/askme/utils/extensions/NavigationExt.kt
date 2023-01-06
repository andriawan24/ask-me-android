package com.andriawan.askme.utils.extensions

import androidx.navigation.NavHostController
import com.andriawan.askme.utils.Constants
import com.andriawan.askme.utils.None
import com.andriawan.askme.utils.SingleEvents

fun NavHostController.handleNavigationWithSingleEvent(
    event: SingleEvents<None>,
    destination: String,
    popUpToRoute: String = Constants.EMPTY,
    inclusive: Boolean = false,
    launchSingleTop: Boolean = false
) {
    event.getContentIfNotHandled()?.let {
        this.navigate(destination) {
            this.launchSingleTop = launchSingleTop
            popUpTo(popUpToRoute) {
                this.inclusive = inclusive
            }
        }
    }
}