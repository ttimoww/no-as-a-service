package com.example.no_as_a_service.service

import io.nats.client.Connection
import io.nats.client.Dispatcher
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class EventService(private val natsConnection: Connection) {

    @PostConstruct
    fun subscribeToAll() {
        val dispatcher: Dispatcher = natsConnection.createDispatcher { msg ->
            val received = String(msg.data)
            println("📩 Received on ${msg.subject}: $received")
        }

        dispatcher.subscribe(">") // '>' = match all subjects
        println("✅ NATS subscription to all subjects established")
    }
}
