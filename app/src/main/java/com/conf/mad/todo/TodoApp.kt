package com.conf.mad.todo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.conf.mad.todo.designsystem.TodoTheme
import com.conf.mad.todo.home.HOME_SCREEN_ROUTE
import com.conf.mad.todo.home.homeScreen
import com.conf.mad.todo.post.postScreen

@Composable
fun TodoApp(
    navController: NavHostController = rememberNavController()
) {
    TodoTheme {
        NavHost(navController = navController, startDestination = HOME_SCREEN_ROUTE) {
            homeScreen()
            postScreen(
                onCancel = navController::popBackStack,
                onComplete = navController::popBackStack
            )
        }
    }
}
