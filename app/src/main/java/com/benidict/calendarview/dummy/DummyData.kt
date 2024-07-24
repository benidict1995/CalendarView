package com.benidict.calendarview.dummy

import com.benidict.model.EventUIModel

data class EventDetails(
    val name: String,
    val description: String
)

val events = listOf(
    EventUIModel(
        date = "2024-07-07",
        startTime = "7:00 AM",
        endTime = "4:00 PM",
        startDate = "2024-07-07",
        endDate = "2024-07-07",
        eventDetails = EventDetails(
            name = "Benidict Birthday",
            description = "Reception is Cavite City"
        )
    ),
    EventUIModel(
        date = "2024-07-20",
        startTime = "10:00 AM",
        endTime = "11:00 AM",
        startDate = "2024-07-20",
        endDate = "2024-07-20",
        eventDetails = EventDetails(
            name = "Daily Stand Up",
            description = "Tech meeting"
        )
    ),
    EventUIModel(
        date = "2024-07-12",
        startTime = "10:00 AM",
        endTime = "2:00 PM",
        startDate = "2024-07-12",
        endDate = "2024-07-16",
        eventDetails = EventDetails(
            name = "Basket Ball Training",
            description = "Ball for life"
        )
    ),
    EventUIModel(
        date = "2024-07-12",
        startTime = "1:00 PM",
        endTime = "2:00 PM",
        startDate = "2024-07-15",
        endDate = "2024-07-15",
        eventDetails = EventDetails(
            name = "Leet Code",
            description = "Code with  the fam"
        )
    ),
    EventUIModel(
        date = "2024-08-3",
        startTime = "2:00 PM",
        endTime = "3:00 PM",
        startDate = "2024-07-3",
        endDate = "2024-07-3",
        eventDetails = EventDetails(
            name = "Connan Vaccine",
            description = "Vaccine Day!"
        )
    )
)