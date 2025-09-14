package naas.nats.events

import kotlinx.serialization.Serializable

@Serializable
class QuestionEvent (val text: String) : BaseEvent