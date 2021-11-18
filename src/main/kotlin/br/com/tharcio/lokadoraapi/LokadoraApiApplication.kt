package br.com.tharcio.lokadoraapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LokadoraApiApplication

fun main(args: Array<String>) {
    runApplication<LokadoraApiApplication>(*args)
}
