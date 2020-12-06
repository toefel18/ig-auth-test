package nl.toefel.resourceserver.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder.FIRST
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Flux

/**
 *
 * Config for spring-web
 *
 * Based on https://kevcodez.de/posts/2020-03-26-secure-spring-boot-app-with-oauth2-aws-cognito/
 */
//@EnableWebSecurity
//class CustomSecurityConfig : WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests { auth -> auth.anyRequest().authenticated() }
//            .oauth2ResourceServer { oauth2 ->
//                oauth2.jwt { jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor()) }
//            }
//    }
//
//    /**
//     * Extracts GrantedAuthorities from the JWT, that spring can use.
//     *
//     * Fetch them with `SecurityContextHolder.getContext().authentication.authorities`
//     */
//    fun grantedAuthoritiesExtractor(): JwtAuthenticationConverter {
//        return JwtAuthenticationConverter().apply {
//            setJwtGrantedAuthoritiesConverter { jwt ->
//                val groups = jwt.claims.getOrDefault("cognito:groups", emptyList<String>()) as List<*>
//                groups.map { SimpleGrantedAuthority(it.toString()) }
//            }
//        }
//    }
//}
