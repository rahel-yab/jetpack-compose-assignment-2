// TodoApiService.kt
package com.example.todoapp.data.network

import com.example.todoapp.data.model.TodoItem
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos") // Adjust the endpoint if necessary
    suspend fun getTodos(): List<TodoItem>
}