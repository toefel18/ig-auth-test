package nl.toefel.resourceserver.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder.FIRST
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher
import reactor.core.publisher.Flux

/**
 * Config for spring-web-reactive
 */
@EnableWebFluxSecurity
class WebSecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .csrf()
                .disable()
            .addFilterAt(RequestLogWebFilter(), FIRST)
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers(HttpMethod.GET, "/actuator/health").permitAll()
                    .pathMatchers(HttpMethod.GET, "/customers/**").hasAuthority("SCOPE_message:read")
                    .pathMatchers(HttpMethod.POST, "/customers/**").hasAuthority("SCOPE_message:write")
                    .anyExchange().authenticated()
            }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { jwt ->
                    jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
                }
            }
            .logout { logout -> logout.requiresLogout(PathPatternParserServerWebExchangeMatcher("/logout")) }

        return http.build()
    }

    fun grantedAuthoritiesExtractor(): ReactiveJwtAuthenticationConverter {
        return ReactiveJwtAuthenticationConverter().apply {
            setJwtGrantedAuthoritiesConverter { jwt: Jwt ->
                println("${jwt.tokenValue}\nwith claims: ${jwt.claims}")
                val groups = jwt.claims.getOrDefault("cognito:groups", emptyList<String>()) as List<*>
                println("groups: $groups")
                Flux.fromIterable(groups.map { SimpleGrantedAuthority(it.toString()) })
            }
        }
    }

}
