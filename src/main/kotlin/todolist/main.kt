package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import spark.Spark.get

fun main(args: Array<String>) {
    val objectMapper = ObjectMapper().registerKotlinModule()
    val jsonTransformer = JsonTransformer(objectMapper)
    val taskRepository = TaskRepository()
    val taskController = TaskController(taskRepository)

    get("/hello") { request, response ->
        val name = request.queryParams("name")
        "Hello ${name ?: "world"}!!"
    }

//    get("/tasks") { request, response ->
//        val tasks = listOf(
//                Task(1, "靴の修理をする", false),
//                Task(2, "旅行の荷物を準備する", true)
//        )
//        objectMapper.writeValueAsString(tasks)
//    }

    get("/tasks", taskController.index(), jsonTransformer)
}
