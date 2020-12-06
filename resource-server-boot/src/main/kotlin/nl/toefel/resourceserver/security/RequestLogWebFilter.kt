package nl.toefel.resourceserver.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit

/**
 * Webflux request log filter
 */
class RequestLogWebFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val start = System.nanoTime()
        return chain.filter(exchange).doFinally {
            val req = exchange.request
            val res = exchange.response
            val pathAndQuery = pathAndQuery(req)
            val reqHeaders = req.headers.map { (h, value) -> "$h: ${value.joinToString(", ")}" }.joinToString("\n")
            val resHeaders = res.headers.map { (h, value) -> "$h: ${value.joinToString(", ")}" }.joinToString("\n")
//            if (!pathAndQuery.startsWith("/actuator/health")) {
                val remoteAddresses = remoteAddressFromRequest(req)
                val durationMillis = TimeUnit.MILLISECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS)
                log.info("${req.method} $pathAndQuery status=${res.statusCode?.value()} duration=${durationMillis}ms remoteAddresses=$remoteAddresses\n--request headers:\n${reqHeaders}\n--response headers:\n$resHeaders")
//            }
        }
    }

    private fun pathAndQuery(req: ServerHttpRequest): String {
        val path = req.path
        val query = if (req.uri.rawQuery != null) "?${req.uri.rawQuery}" else ""
        return "$path$query"
    }

    private fun remoteAddressFromRequest(req: ServerHttpRequest): String {
        val forwardedFor = req.headers["X-Forwarded-For"]
        return if (forwardedFor.isNullOrEmpty())
            req.remoteAddress?.hostString ?: "unknown"
        else
            forwardedFor.joinToString()
    }

    companion object {
        @JvmStatic
        val log: Logger = LoggerFactory.getLogger(RequestLogWebFilter::class.java)
    }
}
