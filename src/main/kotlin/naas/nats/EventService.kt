package naas.nats

import io.nats.client.Connection
import io.nats.client.Dispatcher
import io.nats.client.Message
import jakarta.annotation.PostConstruct
import kotlinx.serialization.json.Json
import naas.nats.events.QuestionEvent
import naas.question.QuestionService
import org.springframework.stereotype.Service

private val jsonWithoutUnknownKeys = Json { ignoreUnknownKeys = true }

@Service
class EventService(
    private val natsConnection: Connection,
    private val questionService: QuestionService
) {
    @PostConstruct
    fun subscribeToAll() {
        val dispatcher: Dispatcher = natsConnection.createDispatcher { msg -> handle(msg) }
        dispatcher.subscribe(">") // '>' = match all subjects
    }


    private fun handle(msg: Message){
        val data = String(msg.data)

        val event = when(msg.subject) {
            "question" -> {
                val questionEvent = jsonWithoutUnknownKeys.decodeFromString<QuestionEvent>(data )
                this.questionService.process(questionEvent)
            }
            else -> {
                println("⚠️ Unknown event subject ${msg.subject}")
            }
        }
    }
}
