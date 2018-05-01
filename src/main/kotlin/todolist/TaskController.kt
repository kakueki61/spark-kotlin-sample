package todolist

import spark.Route

class TaskController {
    fun index() = Route { request, response ->
        listOf(
                Task(1, "clean my room", true),
                Task(2, "buy toilet paper", false)
        )
    }
}
