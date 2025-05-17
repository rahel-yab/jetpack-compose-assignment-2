package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun TodoDetailScreen(navController: NavController, todoId: Int, viewModel: TodoViewModel) {
    val todos = viewModel.todos.collectAsState().value
    val todo = todos.find { it.id == todoId }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color(0xFFE6E0F8) // light purple background
    ) {
        if (todo != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Todo Details",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black
                )

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE6E0F8)),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Title: ${todo.title}",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Completed: ${if (todo.completed) "Yes" else "No"}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (todo.completed) Color.Green else Color.Red
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Text(text = "Back")
                }
            }
        } else {
            Text(
                "Todo not found",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
    }
}

