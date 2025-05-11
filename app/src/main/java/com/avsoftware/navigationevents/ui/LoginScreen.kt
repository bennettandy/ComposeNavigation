package com.avsoftware.navigationevents.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    isLoading: Boolean = false,
    loginClicked: () -> Unit = {}
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Login Screen")
        Button(
            onClick = loginClicked
        ) {
            Text("Login Screen")
        }
    }
}