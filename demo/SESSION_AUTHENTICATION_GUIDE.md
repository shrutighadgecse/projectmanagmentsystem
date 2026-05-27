# Spring Boot Session-Based Authentication Implementation Guide

## Complete Implementation for Secure Login with Back-Button Prevention

This document provides a comprehensive guide for the session-based authentication system implemented in the IMAPG Dashboard application.

---

## 📋 Project Structure

```
src/main/java/
├── com/example/demo/
│   ├── config/
│   │   └── WebConfig.java                 (Interceptor registration & CORS)
│   ├── controller/
│   │   └── EmployeeController.java       (Login/Logout/Registration endpoints)
│   ├── filter/
│   │   └── CacheControlFilter.java       (Cache-control headers)
│   ├── interceptor/
│   │   └── AuthInterceptor.java          (Session validation interceptor)
│   ├── model/
│   │   └── Employee.java                 (Employee entity)
│   ├── repository/
│   │   └── EmployeeRepository.java       (JPA repository)
│   └── service/
│       └── EmployeeService.java          (Business logic)

src/main/resources/
├── templates/
│   ├── login.html                        (Login page)
│   ├── home.html                         (Dashboard/Home page)
│   ├── logout-success.html               (Logout confirmation page)
│   ├── register.html                     (Registration page)
│   ├── success.html                      (Registration success page)
│   └── forgot-password.html              (Password recovery page)
└── static/
    ├── home/
    │   └── homee.js                      (Dashboard JavaScript)
    └── loging/
        └── loginn.css                    (Login styling)
```

---

## 🔐 Security Architecture

### 1. **AuthInterceptor (Server-Side Session Validation)**

**File:** `AuthInterceptor.java`

**Purpose:** 
- Validates HTTP session on every request to secured endpoints
- Redirects unauthenticated users to login page
- Applies cache-control headers to prevent browser caching

**Key Features:**
```java
@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) {
        // Check if request is for public endpoint
        if (isPublicEndpoint(requestURI)) {
            return true;
        }
        
        // Validate session
        HttpSession session = request.getSession(false);
        Employee user = null;
        
        if (session != null) {
            user = (Employee) session.getAttribute("user");
        }
        
        // Redirect to login if no valid user
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        // Set cache-control headers
        setCacheControlHeaders(response);
        return true;
    }
}
```

**Public Endpoints (No Authentication Required):**
- `/` - Login page
- `/register` - Registration page
- `/forgot-password` - Password recovery
- `/logout-success` - Logout confirmation
- `/success` - Registration success
- `/static/**`, `/css/**`, `/js/**`, `/images/**` - Static resources

**Secured Endpoints (Authentication Required):**
- `/home` - Dashboard
- `/api/userDetails` - User info API

---

### 2. **CacheControlFilter (Cache Prevention)**

**File:** `CacheControlFilter.java`

**Purpose:**
- Adds cache-control headers to all HTTP responses
- Prevents browser caching of secured pages
- Ensures "Page Expired" on browser back button

**Headers Applied:**
```
Cache-Control: no-cache, no-store, must-revalidate, private
Pragma: no-cache
Surrogate-Control: no-store
Expires: 0 (past date)
X-Frame-Options: SAMEORIGIN
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
```

---

### 3. **WebConfig (Interceptor Registration)**

**File:** `WebConfig.java`

**Purpose:**
- Registers AuthInterceptor with Spring MVC
- Configures CORS mappings
- Defines path patterns for interceptor

**Configuration:**
```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/register", "/logout-success", "/forgot-password", "/success")
            .excludePathPatterns("/css/**", "/js/**", "/images/**", "/static/**", "/resources/**")
            .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.jpeg", 
                               "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.woff", 
                               "/**/*.woff2", "/**/*.ttf");
}
```

---

### 4. **EmployeeController (Authentication Endpoints)**

**File:** `EmployeeController.java`

**Key Endpoints:**

#### Login
```java
@PostMapping("/login")
public String login(@RequestParam String emailid,
                    @RequestParam String password,
                    Model model,
                    HttpSession session) {
    // Find employee by email
    Employee employee = employeeService.findByEmailid(emailid);
    
    // Validate password
    if (!employee.getCreatepassword().equals(password)) {
        model.addAttribute("error", "Incorrect password");
        return "login";
    }
    
    // Create session
    session.setAttribute("user", employee);
    session.setMaxInactiveInterval(30 * 60); // 30 minutes timeout
    
    return "redirect:/home";
}
```

#### Logout
```java
@GetMapping("/logout")
public String logout(HttpSession session) {
    // Completely invalidate session
    if (session != null) {
        session.invalidate();
    }
    return "redirect:/logout-success";
}
```

#### Home/Dashboard
```java
@GetMapping("/home")
public String homePage(HttpSession session) {
    // Check session exists
    Employee user = (Employee) session.getAttribute("user");
    if (user == null) {
        return "redirect:/";
    }
    return "home";
}
```

#### Get User Details API
```java
@GetMapping("/api/userDetails")
@ResponseBody
public Map<String, Object> getUserDetails(HttpSession session) {
    Employee user = (Employee) session.getAttribute("user");
    Map<String, Object> result = new HashMap<>();
    
    if (user != null) {
        result.put("firstName", user.getEmployeename().split(" ", 2)[0]);
        result.put("emailid", user.getEmailid());
        result.put("department", user.getDepartment());
        result.put("success", true);
    } else {
        result.put("success", false);
        result.put("error", "No user session found");
    }
    
    return result;
}
```

---

## 🎨 Frontend Back-Button Prevention

### Login Page (`login.html`)

```html
<script>
    /**
     * Prevent browser back button after logout
     */
    function preventBackButton() {
        window.history.pushState(null, "", window.location.href);
        
        window.onpopstate = function() {
            window.history.pushState(null, "", window.location.href);
        };
    }

    document.addEventListener('DOMContentLoaded', function() {
        preventBackButton();
    });
</script>
```

**Cache Control Meta Tags:**
```html
<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">
<meta http-equiv="Cache-Control" content="post-check=0, pre-check=0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
```

---

### Home Page (`home.html`)

**Enhanced Security Script:**

```javascript
/**
 * Prevent browser back button from navigating away from home page
 */
(function() {
    'use strict';

    function preventBackButton() {
        // Push current URL to history
        window.history.pushState(null, "", window.location.href);
        
        // Handle pop state (back button click)
        window.addEventListener('popstate', function() {
            window.history.pushState(null, "", window.location.href);
        });
    }

    function verifySessionValidity() {
        fetch('/api/userDetails')
            .then(response => response.json())
            .then(data => {
                if (!data.success) {
                    // Session invalid, redirect to login
                    window.location.href = '/';
                }
            })
            .catch(error => {
                window.location.href = '/';
            });
    }

    function handleVisibilityChange() {
        document.addEventListener('visibilitychange', function() {
            if (!document.hidden) {
                // User returned to tab, verify session
                verifySessionValidity();
            }
        });
    }

    document.addEventListener('DOMContentLoaded', function() {
        preventBackButton();
        handleVisibilityChange();
    });
})();
```

---

### Logout Success Page (`logout-success.html`)

```javascript
/**
 * Prevent returning to home after logout
 */
function preventBackNavigation() {
    sessionStorage.clear();
    window.history.pushState(null, "", window.location.href);
    
    window.addEventListener('popstate', function() {
        window.location.href = '/';
    });
}
```

---

## 🔄 Complete User Flow

### 1. **User Login**
```
User submits login form
  ↓
EmployeeController.login() validates credentials
  ↓
Session created with user data
  ↓
Redirect to /home
  ↓
AuthInterceptor verifies session (PASS)
  ↓
Home page rendered with cache-control headers
```

### 2. **User on Home Page**
```
User clicks browser back button
  ↓
JavaScript preventDefault (history.pushState) keeps page in place
  ↓
Session remains valid on server
  ↓
User stays on home page
```

### 3. **Session Expires or Invalid**
```
User tries to access /home without session
  ↓
AuthInterceptor checks session (FAIL)
  ↓
AuthInterceptor redirects to /login
  ↓
User returns to login page
```

### 4. **User Logout**
```
User clicks logout button
  ↓
EmployeeController.logout() called
  ↓
Session invalidated (session.invalidate())
  ↓
Redirect to /logout-success
  ↓
Page displays logout message
  ↓
JavaScript prevents back navigation to /home
  ↓
Trying back button redirects to /login
```

---

## 🔧 Configuration Properties

**application.properties:**
```properties
# Session timeout (30 minutes in milliseconds)
server.servlet.session.timeout=30m

# Enable session persistence
server.servlet.session.persistent=true

# Session cookie settings
server.servlet.session.cookie.secure=false      # true for HTTPS
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.same-site=Lax
```

---

## 🧪 Testing Scenarios

### Test 1: Prevent Back Button After Logout
```
1. Login successfully
2. Click Logout
3. On logout-success page, try browser back button
4. Expected: Stay on logout-success page, cannot reach home page
5. Actual: Page stays at logout-success (history.pushState prevents navigation)
```

### Test 2: Session-Based Access Control
```
1. Access /home directly without session
2. Expected: Redirected to / (login page)
3. Actual: AuthInterceptor validates session, redirects to login
```

### Test 3: Prevent Browser Cache
```
1. Login and navigate to home
2. Use browser dev tools → Network tab → disable cache
3. Click logout
4. Try to go back
5. Expected: "Page Expired" or redirect to login
6. Actual: CacheControlFilter headers prevent caching
```

### Test 4: Session Timeout
```
1. Login to application
2. Wait 30+ minutes
3. Try to access /home
4. Expected: Session expired, redirect to login
5. Actual: AuthInterceptor checks session, finds it null, redirects to login
```

### Test 5: Invalid/Null Session
```
1. Clear browser cookies/session storage manually
2. Access /home directly
3. Expected: Redirect to login
4. Actual: No session found, AuthInterceptor redirects to login
```

---

## ⚠️ Important Notes

### Security Considerations

1. **Password Security:**
   - Current implementation stores passwords as plain text
   - **PRODUCTION:** Use BCryptPasswordEncoder
   ```java
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   ```

2. **HTTPS:**
   - Enable `secure=true` in session cookies for HTTPS-only
   - Set `SameSite=Strict` for enhanced CSRF protection

3. **Session Fixation:**
   - Current implementation is vulnerable to session fixation attacks
   - **RECOMMENDATION:** Create new session after login:
   ```java
   session.invalidate();
   HttpSession newSession = request.getSession(true);
   newSession.setAttribute("user", employee);
   ```

4. **CSRF Protection:**
   - Add CSRF tokens to forms if enabling Spring Security
   - Current custom auth doesn't prevent CSRF attacks

### Browser Compatibility

- **history.pushState:** IE 10+, all modern browsers ✓
- **Cache-Control headers:** All browsers ✓
- **Session cookies:** All browsers ✓

### Limitations

1. **history.pushState:**
   - JavaScript-based, can be bypassed
   - Not a security feature, only UX enhancement
   - Server-side session validation is the real security layer

2. **Cache-Control Headers:**
   - Respects HTTP standards
   - Some older proxies may ignore
   - Always rely on server-side session validation

---

## 📦 Dependencies

**pom.xml:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
</dependency>
```

---

## 🚀 Deployment Checklist

- [ ] Enable HTTPS/SSL
- [ ] Change session timeout to appropriate value
- [ ] Implement password encryption (BCrypt)
- [ ] Add CSRF token protection
- [ ] Enable secure session cookies (secure=true)
- [ ] Set X-Frame-Options header
- [ ] Enable CORS only for trusted domains
- [ ] Configure Spring Security for additional protection
- [ ] Set up logging for authentication failures
- [ ] Test all security scenarios

---

## 📞 Troubleshooting

### Issue: User can still go back to home page after logout
**Solution:** Verify cache-control headers are sent by server (check Network tab in dev tools)

### Issue: Session not persisting across requests
**Solution:** Check session.getAttribute("user") is not null, verify session timeout setting

### Issue: AuthInterceptor not working
**Solution:** Verify WebConfig.addInterceptors() is being called, check excluded path patterns

### Issue: Static resources getting 401 Unauthorized
**Solution:** Add static resource paths to excludePathPatterns() in WebConfig

---

**Version:** 1.0  
**Last Updated:** February 3, 2026  
**Author:** IMAPG Development Team
