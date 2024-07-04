package com.benidict.model

data class CalendarUIModel<T>(
    val id: Int?,
    val day: String?,
    val date: String?,
    val events: List<T>?
)