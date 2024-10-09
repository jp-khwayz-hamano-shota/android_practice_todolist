package jp.khwayz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.khwayz.jettodoapp.Task
import jp.khwayz.jettodoapp.TaskDao
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val taskDao: TaskDao) : ViewModel() {
    var title by mutableStateOf("")
    var description by mutableStateOf("")

    var isShowDialog by mutableStateOf(false)

    val tasks = taskDao.loadAllTasks().distinctUntilChanged()

    private var editingTask: Task? = null
    val isEditing: Boolean
        get() = editingTask != null

    fun setEditingTask(task: Task){
        editingTask = task
        title = task.title
        description = task.description
    }

    fun updateTask(){
        //let　nullの場合はletスコープ内が実行されない
        editingTask?.let {task ->
            viewModelScope.launch {
                task.title = title
                task.description = description
                taskDao.updateTask(task)
            }
        }
    }

    fun resetProperties(){
        editingTask = null
        title = ""
        description = ""
    }

    fun createTask() {
        viewModelScope.launch {
            val newTask = Task(title = title, description = description)
            taskDao.insertTask(newTask)
        }
    }

    fun deleteTask(delTask: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(delTask)
        }
    }

    fun showTask() {
        viewModelScope.launch {

        }
    }
}