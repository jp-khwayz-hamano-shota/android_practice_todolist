package jp.khwayz.jettodoapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import jp.khwayz.jettodoapp.Task

@Composable
fun TaskList(
    tasks: List<Task>,
    onClickRow: (Task) -> Unit,
    onClickDelete: (Task) -> Unit,
) {
    LazyColumn {
        items(tasks) { task ->
            TaskRow(
                task = task,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete,
            )
        }
    }

}