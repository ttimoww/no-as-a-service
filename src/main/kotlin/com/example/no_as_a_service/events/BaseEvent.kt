package com.example.no_as_a_service.events

import kotlinx.serialization.Serializable

@Serializable
sealed interface BaseEvent {
    val type: String;
}