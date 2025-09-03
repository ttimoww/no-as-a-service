package com.example.no_as_a_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.nats.client.Connection
import io.nats.client.Nats

@Configuration
class NatsConfig {

    @Bean
    fun natsConnection(): Connection {
        return Nats.connect("nats://nats.timowernars.nl:4222")
    }
}