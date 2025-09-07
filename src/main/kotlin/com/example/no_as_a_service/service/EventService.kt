package com.example.no_as_a_service.service

import com.example.no_as_a_service.events.QuestionEvent
import io.nats.client.Connection
import io.nats.client.Dispatcher
import io.nats.client.Message
import jakarta.annotation.PostConstruct
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service

private val jsonWithoutUnknownKeys = Json { ignoreUnknownKeys = true }

@Service
class EventService(private val natsConnection: Connection) {

    @PostConstruct
    fun subscribeToAll() {
        val dispatcher: Dispatcher = natsConnection.createDispatcher { msg -> handle(msg) }

        dispatcher.subscribe(">") // '>' = match all subjects
        println("✅ NATS subscription to all subjects established")
    }

    private fun handle(msg: Message){
        val data = String(msg.data)
        val question = jsonWithoutUnknownKeys.decodeFromString<QuestionEvent>(data )

        println("‼️ Question: ${question.text}")
    }
}
