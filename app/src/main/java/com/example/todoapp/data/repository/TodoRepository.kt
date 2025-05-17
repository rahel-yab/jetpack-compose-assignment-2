package com.example.todoapp.data.repository

import com.example.todoapp.data.database.TodoDao
import com.example.todoapp.data.model.TodoItem

class TodoRepository(private val todoDao: TodoDao) {
    suspend fun getTodos(): List<TodoItem> {
        return todoDao.getAllTodos()
    }

    suspend fun insertTodos(todos: List<TodoItem>) {
        todoDao.insertTodos(todos)
    }
}
