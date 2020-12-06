package nl.toefel.resourceserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.Base64

@SpringBootApplication
class ResourceServerApplication

fun main(args: Array<String>) {

	println(Base64.getEncoder().encodeToString("2s5uvggro3karqb05g1m0ml0e5:b420vqo99to04s7m63hd34tne5kh7dgnheqsrntpcb8t3jie4dq".toByteArray()))

	runApplication<ResourceServerApplication>(*args)
}
