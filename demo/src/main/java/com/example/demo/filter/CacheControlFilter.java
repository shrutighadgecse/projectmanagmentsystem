package com.example.demo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * CacheControlFilter: Adds cache-control headers to all responses.
 * Ensures that secured pages are not cached by browser or proxy.
 * This filter applies to all HTTP responses.
 */
@Component
public class CacheControlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();

        // Apply cache-control headers only to non-static resources
        if (!isStaticResource(requestURI)) {
            // Set cache-control headers to prevent caching
            setCacheControlHeaders(httpResponse);
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    /**
     * Set cache-control headers on response
     */
    private void setCacheControlHeaders(HttpServletResponse response) {
        // HTTP 1.1 cache-control
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");

        // HTTP 1.0 compatibility
        response.setHeader("Pragma", "no-cache");

        // Proxies should not cache
        response.setHeader("Surrogate-Control", "no-store");

        // Set expiration to past date
        response.setDateHeader("Expires", 0);

        // Security headers
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-XSS-Protection", "1; mode=block");
    }

    /**
     * Check if the request URI is for a static resource
     */
    private boolean isStaticResource(String uri) {
        return uri.contains(".css") ||
               uri.contains(".js") ||
               uri.contains(".jpg") ||
               uri.contains(".jpeg") ||
               uri.contains(".png") ||
               uri.contains(".gif") ||
               uri.contains(".svg") ||
               uri.contains(".woff") ||
               uri.contains(".woff2") ||
               uri.contains(".ttf") ||
               uri.contains(".eot") ||
               uri.startsWith("/static/") ||
               uri.startsWith("/resources/") ||
               uri.startsWith("/css/") ||
               uri.startsWith("/js/") ||
               uri.startsWith("/images/");
    }
}
