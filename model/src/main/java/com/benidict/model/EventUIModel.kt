package com.benidict.model

data class EventUIModel<T> (
    val date: String,
    val startTime: String,
    val endTime: String,
    val startDate: String,
    val endDate: String,
    val eventDetails: T?
)