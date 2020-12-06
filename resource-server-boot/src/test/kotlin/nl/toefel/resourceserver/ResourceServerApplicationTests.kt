package nl.toefel.resourceserver

import com.nimbusds.jose.util.Base64
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ResourceServerApplicationTests {

	@Test
	fun contextLoads() {
		Base64.encode("2s5uvggro3karqb05g1m0ml0e5:b420vqo99to04s7m63hd34tne5kh7dgnheqsrntpcb8t3jie4dq").toJSONString().also { println(it) }
	}

}
