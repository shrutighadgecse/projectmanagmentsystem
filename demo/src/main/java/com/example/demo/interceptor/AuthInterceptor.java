package com.example.demo.interceptor;

import com.example.demo.model.Employee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * AuthInterceptor: Validates session for all secured endpoints.
 * If session is invalid, redirects to login page.
 * Also handles cache-control headers to prevent browser caching of secured pages.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        String requestURI = request.getRequestURI();
        
        // Public endpoints that don't require authentication
        if (isPublicEndpoint(requestURI)) {
            return true;
        }
        
        // Check session for secured endpoints
        HttpSession session = request.getSession(false);
        Employee user = null;
        
        if (session != null) {
            user = (Employee) session.getAttribute("user");
        }
        
        // If no valid user in session, redirect to login
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        // Set cache-control headers for secured pages to prevent browser caching
        setCacheControlHeaders(response);
        
        return true;
    }

    /**
     * Checks if the endpoint is public and doesn't require authentication
     */
    private boolean isPublicEndpoint(String uri) {
        return uri.equals("/") || 
               uri.equals("/register") || 
               uri.equals("/about") || 
               uri.equals("/mission") || 
               uri.equals("/contact") || 
               uri.equals("/login") ||
               uri.startsWith("/css/") || 
               uri.startsWith("/js/") || 
               uri.startsWith("/images/") || 
               uri.startsWith("/static/") || 
               uri.contains(".css") || 
               uri.contains(".js") || 
               uri.contains(".jpg") || 
               uri.contains(".jpeg") || 
               uri.contains(".png") || 
               uri.contains(".gif") ||
               uri.contains(".svg") ||
               uri.equals("/forgot-password") ||
               uri.equals("/logout-success") ||
               uri.equals("/success") ||
               uri.startsWith("/forgot-password/") ||
               uri.equals("/api/checkEmail");
    }

    /**
     * Sets cache-control headers to prevent caching of secured pages
     */
    private void setCacheControlHeaders(HttpServletResponse response) {
        // Prevent caching in HTTP 1.1 browsers
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        
        // Prevent caching in HTTP 1.0 browsers
        response.setHeader("Pragma", "no-cache");
        
        // Set expiration to past date
        response.setHeader("Expires", "0");
        
        // Prevent caching by proxies
        response.setHeader("Surrogate-Control", "no-store");
        
        // Disable browser cache for HTTPS
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-XSS-Protection", "1; mode=block");
    }
}
