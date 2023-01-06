package com.andriawan.askme.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun LifecycleComposable(onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit) {
    val owner = LocalLifecycleOwner.current
    DisposableEffect(owner.lifecycle) {
        val observer = LifecycleEventObserver(onEvent)
        owner.lifecycle.addObserver(observer)
        onDispose {
            owner.lifecycle.removeObserver(observer)
        }
    }
}