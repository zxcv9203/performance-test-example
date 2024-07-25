package org.example.performancetestexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PerformanceTestExampleApplication

fun main(args: Array<String>) {
    runApplication<PerformanceTestExampleApplication>(*args)
}
