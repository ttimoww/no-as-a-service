package com.example.no_as_a_service.events

import kotlinx.serialization.Serializable

@Serializable
class QuestionEvent (
    override val type: String,
    val text: String
) : BaseEvent