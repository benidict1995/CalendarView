package com.benidict.calendarview.dummy

import com.benidict.model.EventUIModel

data class EventDetails(
    val name: String,
    val description: String
)

val events = listOf(
    EventUIModel(
        date = "2024-07-07",
        startDate = "2024-07-07",
        endDate = "2024-07-07",
        eventDetails = EventDetails(
            name = "Benidict Birthday",
            description = "Reception is Cavite City"
        )
    ),
    EventUIModel(
        date = "2024-07-12",
        startDate = "2024-07-12",
        endDate = "2024-07-16",
        eventDetails = EventDetails(
            name = "Basket Ball Training",
            description = "Ball for life"
        )
    ),
    EventUIModel(
        date = "2024-08-3",
        startDate = "2024-07-3",
        endDate = "2024-07-3",
        eventDetails = EventDetails(
            name = "Connan Vaccine",
            description = "Vaccine Day!"
        )
    )
)