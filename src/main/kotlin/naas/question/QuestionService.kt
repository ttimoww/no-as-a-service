package naas.question

import jakarta.transaction.Transactional
import naas.nats.events.QuestionEvent
import org.springframework.stereotype.Service

/**
 * This adds an extension function to the QuestionEntity class which is only available inside this file.
 * Use 'internal' instead of 'private' to make it available in the entire package.
 */
private fun QuestionEvent.toEntity(): QuestionEntity {
    return QuestionEntity(question = text)
}

@Service
class QuestionService(
    private val questionRepository: QuestionRepository
) {

    @Transactional
    fun process(question: QuestionEvent): QuestionEntity {
        val entity = question.toEntity()
        return this.questionRepository.save(entity)
    }

    fun findAll(): List<QuestionEntity> {
        return questionRepository.findAll()
    }
}