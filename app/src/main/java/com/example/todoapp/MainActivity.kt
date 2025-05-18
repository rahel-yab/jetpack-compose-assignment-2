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
import com.example.todoapp.data.network.TodoApiService
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.ui.screens.TodoDetailScreen
import com.example.todoapp.ui.screens.TodoListScreen
import com.example.todoapp.viewmodel.TodoViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize Room
        val db = TodoDatabase.getDatabase(applicationContext)
        val todoDao = db.todoDao()

        // 2. Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(TodoApiService::class.java)

        // 3. Create repository with both DAO and API
        val repository = TodoRepository(todoDao, apiService)

        // 4. Create ViewModel
        viewModel = TodoViewModel(repository)

        // 5. Set up Compose UI
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
