package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Route
import spark.Spark.halt

class TaskController(private val objectMapper: ObjectMapper, val taskRepository: TaskRepository) {
    fun index() = Route { _, _ ->
        taskRepository.findAll()
    }

    fun create() = Route { req, res ->
        val request = objectMapper.readValue<TaskCreateRequest>(req.bodyAsBytes()) ?: throw halt(400)
        val task = taskRepository.create(request.content)
        res.status(201)
        task
    }
}
