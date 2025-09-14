package naas.question

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QuestionController (
    private val questionService: QuestionService
) {
    @GetMapping("/")
    fun getAll(): List<QuestionDto> {
        val questions = this.questionService.findAll()

        // Alternative is questions.map()
        val dtos = mutableListOf<QuestionDto>()
        for (q in questions) {
            dtos.add(QuestionDto(q.question))
        }

        return dtos
    }
}