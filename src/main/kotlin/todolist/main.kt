package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import spark.Spark.*

fun main(args: Array<String>) {
    val objectMapper = ObjectMapper().registerKotlinModule()
    val jsonTransformer = JsonTransformer(objectMapper)
    val taskRepository = TaskRepository()
    val taskController = TaskController(objectMapper, taskRepository)

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

    path("/tasks") {
        get("", taskController.index(), jsonTransformer)
        post("", taskController.create(), jsonTransformer)
        get("/:id", taskController.show(), jsonTransformer)
        patch("/:id", taskController.update(), jsonTransformer)
        delete("/:id", taskController.destroy(), jsonTransformer)
    }
}
