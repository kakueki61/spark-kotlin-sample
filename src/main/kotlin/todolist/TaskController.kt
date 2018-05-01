package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Request
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

    fun show() = Route { request, response ->
        request.task ?: throw halt(404)
    }

    fun destroy() = Route { request, response ->
        val task = request.task ?: throw halt(404)
        taskRepository.delete(task)
        response.status(204)
    }

    private val Request.task: Task?
        get() {
            val id = params("id").toLongOrNull()
            return id?.let(taskRepository::findById)
        }
}
