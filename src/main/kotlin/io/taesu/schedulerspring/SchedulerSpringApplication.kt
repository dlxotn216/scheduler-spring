package io.taesu.schedulerspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SchedulerSpringApplication

fun main(args: Array<String>) {
    runApplication<SchedulerSpringApplication>(*args)
}
