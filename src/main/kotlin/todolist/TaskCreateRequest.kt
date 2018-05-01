package todolist

import com.fasterxml.jackson.annotation.JsonProperty

class TaskCreateRequest(@JsonProperty("content", required = true) val content: String)
