package jp.khwayz.jettodoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    // idがvalの理由は、ユーザが変更するのことない値のため
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // title、descriptionがvarの理由は、ユーザが値を変更する可能性があるため
    var title: String,
    var description: String
)
