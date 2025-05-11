package com.avsoftware.navigationevents

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avsoftware.navigationevents.ui.DetailsScreen
import com.avsoftware.navigationevents.ui.LoginScreen
import com.avsoftware.navigationevents.ui.MainViewModel
import com.avsoftware.navigationevents.ui.NavigationEvent
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun MainNavigationHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Surface(
        modifier = modifier
    ) {
        NavHost(
            navController,
            startDestination = "login"
        ) {
            composable("login") {

                val viewModel: MainViewModel = viewModel()

                val state = viewModel.state2

                val lifecycleOwner = LocalLifecycleOwner.current

                // re launch when STARTED - complex
                LaunchedEffect(lifecycleOwner.lifecycle) {
                    lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.navigationalEventsFlow.collect {
                            event -> when (event){
                                is NavigationEvent.LoggedIn -> navController.navigate("details")
                            }
                        }
                    }
                }

                LoginScreen(
                    isLoading = state.value.isLoading,
                    loginClicked = { viewModel.loginClicked() }
                )
            }

            composable("details") {

                DetailsScreen()
            }
        }
    }
}