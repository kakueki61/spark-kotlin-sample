package todolist

class TaskRepository {
    private val tasks = mutableListOf<Task>()

    private val maxId: Long
        get() = tasks.map(Task::id).max() ?: 0

    fun findAll() = tasks.toList()

    fun create(content: String): Task {
        val id = maxId + 1
        val task = Task(id, content, false)
        tasks.add(task)
        return task
    }
}
