package com.example.todoapp.data.repository

import com.example.todoapp.data.database.TodoDao
import com.example.todoapp.data.model.TodoItem
import com.example.todoapp.data.network.TodoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(
    private val todoDao: TodoDao,
    private val apiService: TodoApiService
) {

    suspend fun getTodos(): List<TodoItem> = withContext(Dispatchers.IO) {
        // First, return cached data (immediate UI update)
        val cachedTodos = todoDao.getAllTodos()

        try {
            // Try fetching from network
            val remoteTodos = apiService.getTodos()
                .take(20) // optional: limit to 20 items

            // Save fresh data to Room
            todoDao.insertTodos(remoteTodos)

            // Return updated data
            return@withContext remoteTodos

        } catch (e: Exception) {
            // On error, fallback to local data
            return@withContext cachedTodos
        }
    }

    suspend fun insertTodos(todos: List<TodoItem>) {
        todoDao.insertTodos(todos)
    }
}

