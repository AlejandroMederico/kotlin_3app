package com.mederico.android.todoapp

public sealed class TaskCategory ( var isSelect : Boolean = true) {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}