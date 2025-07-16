package com.example.webstorebackend.common.auth

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class UserContextFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val userIdHeader = httpRequest.getHeader("X-User-Id")
        val userId = userIdHeader?.toLongOrNull()

        UserContextHolder.setUserId(userId)

        try {
            chain.doFilter(request, response)
        } finally {
            UserContextHolder.clear()
        }
    }
}
