fun main() {
    embeddedServer(Netty, host = "0.0.0.0", port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, world!", ContentType.Text.Plain)
            }
            get("/sum") {
                val numbers = call.request.queryParameters.getAll("num")?.map { it.toInt() } ?: listOf()
                val sum = numbers.sum()
                call.respondText("Sum: $sum", ContentType.Text.Plain)
            }
        }
    }.start(wait = true)
}

