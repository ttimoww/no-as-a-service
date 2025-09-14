package naas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NoAsAServiceApplication

fun main(args: Array<String>) {
	runApplication<NoAsAServiceApplication>(*args)
}