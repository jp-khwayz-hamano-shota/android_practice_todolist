package jp.khwayz.jettodoapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

@Dao
interface TaskDao {
    //非同期ワンショットクエリ：１回だけクエリが実行され、データベースのスナップショットを返す
    //オブザーブルクエリ：テーブルに変更があるたびに、新しいデータを取得しにいくクエリ

    //suspendをつけることで非同期ワンショットクエリに行われるようになる
    @Insert
    suspend fun insertTask(task: Task)

    //戻り値にFlowを付けることでオブザーブルクエリにすることができる
    @Query("SELECT * FROM Task")
    fun loadAllTasks(): Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}