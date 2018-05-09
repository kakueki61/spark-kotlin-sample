package todolist

class TaskRepository {
    private val tasks = mutableListOf<Task>()

    private val maxId: Long
        get() = tasks.map(Task::id).max() ?: 0

    fun findAll() = tasks.toList()

    fun findById(id: Long) = tasks.find { it.id == id }

    fun create(content: String): Task {
        val id = maxId + 1
        val task = Task(id, content, false)
        tasks.add(task)
        return task
    }

    fun delete(task: Task) {
        tasks.removeIf { (id) -> id == task.id }
    }

    fun update(task: Task) {
        tasks.replaceAll {
            if (it.id == task.id) task
            else it
        }
    }
}
