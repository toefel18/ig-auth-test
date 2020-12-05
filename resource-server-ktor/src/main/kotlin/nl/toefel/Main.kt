package nl.toefel

import com.auth0.jwk.JwkProviderBuilder
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import kotlin.random.Random

val log: Logger = LoggerFactory.getLogger("main")

data class HelloResponse(val first: Int, val name: String)

data class Session(val name: String, val value: String, val age: Int)

fun Application.module() {

    install(CallLogging) {
        this.logger = log
    }
    install(ContentNegotiation) {
        jackson {
            jacksonObjectMapper()
        }
    }

    install(Authentication) {
        val audience = ":"
        val issuer = "https://cognito-idp.eu-west-1.amazonaws.com/eu-west-1_E6N1rteFZ"
        val jwkProvider = JwkProviderBuilder(issuer)
            .cached(10, 24, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build()

        jwt("jwtauth") {
            realm = issuer
            verifier({ id -> log.info(id); null }, issuer)
            validate { credential -> if (credential.payload.audience.contains(audience)){
                log.info("CREDENTIAL $credential")
                JWTPrincipal(credential.payload)
            } else{
                log.info("CREDENTIAL $credential")
                null
            } }
        }
    }

//    install(Authentication) {
//        oauth("gitHubOAuth") {
//            client = HttpClient(Apache)
//            providerLookup = { loginProviders[application.locations.resolve<login>(login::class, this).type] }
//            urlProvider = { url(login(it.name)) }
//        }
//    }

//    fun AuthenticationPipeline.jwtAuthentication(jwkProvider: JwkProvider, issuer: String, realm: String, validate: (JWTCredential) -> Principal?)

    routing {

        get("/") {
            log.info("request at ${context} ${this.context.parameters} ${this.context.request.queryParameters}")
            call.respond(HelloResponse(1, "Appie"))
        }
        get("/blaat") {
            val session = call.sessions.get<Session>()
            if (session == null) {
                call.sessions.set(Session(call.parameters.get("name") ?: "unknown", "val", Random.nextInt()))
            }
            call.respondText("aap ${session?.name}")
        }
        authenticate("jwtauth") {
            get("/secured/customer") {
                val session = call.sessions.get<Session>()
                if (session == null) {
                    call.sessions.set(Session(call.parameters.get("name") ?: "unknown", "val", Random.nextInt()))
                }
                call.respondText("aap ${session?.name}")
            }
        }
    }
}


fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("./"), module = Application::module).start()
}