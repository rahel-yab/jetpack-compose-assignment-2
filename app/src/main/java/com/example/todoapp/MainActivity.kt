package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.database.TodoDatabase
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.ui.screens.TodoDetailScreen
import com.example.todoapp.ui.screens.TodoListScreen
import com.example.todoapp.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Get your Room database instance
        val db = TodoDatabase.getDatabase(applicationContext)

        // 2. Get the TodoDao
        val todoDao = db.todoDao()

        // 3. Create repository
        val repository = TodoRepository(todoDao)

        // 4. Create ViewModel, passing repository
        viewModel = TodoViewModel(repository)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "list") {
                        composable("list") {
                            TodoListScreen(navController = navController, viewModel = viewModel)
                        }
                        composable("detail/{todoId}") { backStackEntry ->
                            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
                            if (todoId != null) {
                                TodoDetailScreen(
                                    navController = navController,
                                    todoId = todoId,
                                    viewModel = viewModel
                                )
                            } else {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
