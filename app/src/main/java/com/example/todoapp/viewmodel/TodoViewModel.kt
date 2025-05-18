package com.example.todoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.model.TodoItem
import com.example.todoapp.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val current = repository.getTodos()
                if (current.isEmpty()) {
                    val dummyTodos = listOf(
                        TodoItem(7, "Finish Jetpack assignment 2", false),
                        TodoItem(8, "Push code to GitHub", true),
                        TodoItem(9, "Test on emulator", false),
                        TodoItem(10, "Submit assignment", true),
                        TodoItem(11, "Study For Finals", false),
                        TodoItem(12, "Prepare presentation", true),
                        TodoItem(13, "Check emails", false),
                        TodoItem(14, "Plan the week", false),
                        TodoItem(15, "Clean workspace", true),
                        TodoItem(16, "Solve DSA problems", false),
                        TodoItem(17, "Write blog post", true),
                        TodoItem(18, "Team meeting at 3 PM", false),
                        TodoItem(19, "Work on mobile project", false),
                        TodoItem(20, "Code review session", true)
                    )
                    repository.insertTodos(dummyTodos)
                }

                // Now load todos again
                _todos.value = repository.getTodos()
            } catch (e: Exception) {
                Log.e("TodoViewModel", "Error loading todos: ${e.message}")
            }
        }
    }
}


