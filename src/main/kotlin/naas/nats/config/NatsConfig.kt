package naas.nats.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.nats.client.Connection
import io.nats.client.Nats

@Configuration
class NatsConfig {

    @Value($$"${nats.url}")
    private lateinit var natsUrl: String

    @Bean
    fun natsConnection(): Connection {
        return Nats.connect(natsUrl)
    }
}
