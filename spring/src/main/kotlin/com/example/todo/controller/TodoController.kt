package com.example.todo.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/todos", produces = ["application/json"], consumes = ["application/json"])
class TodoController {
    private val todos: MutableMap<UUID, Todo> = mutableMapOf()

    @GetMapping
    fun getTodos(): List<Todo> {
        return todos.values.toList()
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: UUID): Todo {
        return todos[id]!!
    }

    @PostMapping
    fun createTodo(@RequestBody request: CreateTodoRequest): Todo {
        val todo = Todo(UUID.randomUUID(), request.title)
        todos[todo.id] = todo
        return todo
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: UUID) {
        todos.remove(id)
    }
}

data class CreateTodoRequest(val title: String)
data class Todo(val id: UUID, val title: String)