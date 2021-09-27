package io.redspark.candidatos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CandidatosApplication

fun main(args: Array<String>) {
	runApplication<CandidatosApplication>(*args)
}
