package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OtpService;
import com.example.demo.service.EmailService;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;


/**
 * EmployeeController: Handles employee authentication (login/logout) and registration.
 * Uses HTTP Session for maintaining user context.
 * All secured endpoints are protected by AuthInterceptor.
 */
@Controller
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    /**
     * Show landing page (default landing page)
     * If user is already logged in, redirect to home
     */
    @GetMapping("/")
    public String showLandingPage(HttpSession session, Model model) {
        // If user is already logged in, redirect to home
        Employee user = (Employee) session.getAttribute("user");
        if (user != null) {
            return "redirect:/home";
        }
        return "index";
    }

    /**
     * Show login page
     * If user is already logged in, redirect to home
     */
    @GetMapping("/login")
    public String showLoginForm(HttpSession session, Model model) {
        // If user is already logged in, redirect to home
        Employee user = (Employee) session.getAttribute("user");
        if (user != null) {
            return "redirect:/home";
        }
        return "login";
    }

    /**
     * Show registration page
     */
    @GetMapping("/register")
    public String showRegistrationForm(HttpSession session) {
        // If user is already logged in, redirect to home
        Employee user = (Employee) session.getAttribute("user");
        if (user != null) {
            return "redirect:/home";
        }
        return "register";
    }

    /**
     * Handle employee registration
     * Validates password match and checks for duplicate email
     */
    @PostMapping("/register")
    public String registerEmployee(@RequestParam String employeename,
                                   @RequestParam String emailid,
                                   @RequestParam String employeecontact,
                                   @RequestParam String department,
                                   @RequestParam String createpassword,
                                   @RequestParam String confirmpassword,
                                   Model model,
                                   HttpSession session) {

        // If user is already logged in, redirect to home
        Employee existingUser = (Employee) session.getAttribute("user");
        if (existingUser != null) {
            return "redirect:/home";
        }

        // Trim and lowercase email for consistency
        emailid = emailid.trim().toLowerCase();

        // Validate password match
        if (!createpassword.equals(confirmpassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        // Check if email already exists (case-insensitive)
        Employee existingEmail = employeeService.findByEmailid(emailid);
        if (existingEmail != null) {
            model.addAttribute("error", "This email is already registered. Please use a different email address or log in.।");
            return "register";
        }

        // Create and save employee
        Employee employee = new Employee();
        employee.setEmployeename(employeename.trim());
        employee.setEmailid(emailid);
        employee.setEmployeecontact(employeecontact.trim());
        employee.setDepartment(department);
        employee.setCreatepassword(createpassword);
        employee.setConfirmpassword(confirmpassword);

        try {
            employeeService.registerEmployee(employee);
            model.addAttribute("success", "Registration successful! Please login.");
            return "redirect:/success";
        } catch (RuntimeException e) {
            logger.error("Registration failed for email {}", emailid, e);
            String msg = e.getMessage() != null ? e.getMessage() : "Registration error. Please try again.";
            model.addAttribute("error", "Registration error: " + msg);
            return "register";
        }
    }

    /**
     * Handle login request
     * Authenticates user and creates session
     */
    @PostMapping("/login")
    public String login(@RequestParam String emailid,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        
        // If user is already logged in, redirect to home
        Employee existingUser = (Employee) session.getAttribute("user");
        if (existingUser != null) {
            return "redirect:/home";
        }

        // Trim and lowercase email for consistency
        emailid = emailid.trim().toLowerCase();

        // Find employee by email
        Employee employee = employeeService.findByEmailid(emailid);
        if (employee == null) {
            model.addAttribute("error", "Email Not registered  ");
            return "login";
        }
        
        // Validate password
        if (!employee.getCreatepassword().equals(password)) {
            model.addAttribute("error", "Password incorrect ");
            return "login";
        }
        
        // Set user in session and create a new session ID (prevent session fixation attack)
        session.setAttribute("user", employee);
        session.setMaxInactiveInterval(30 * 60); // 30 minutes session timeout
        
        return "redirect:/home";
    }

    /**
     * Display home/dashboard page
     * Protected by AuthInterceptor - requires valid session
     */
    @GetMapping("/home")
    public String homePage(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        return "home";
    }

    /**
     * Display manufacturing details page
     */
    @GetMapping("/manufacturing-details")
    public String manufacturingDetailsPage(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        return "manufacturing-details";
    }

    /**
     * Display dispatch details page
     */
    @GetMapping("/dispatch-details")
    public String dispatchDetailsPage(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        return "dispatch-details";
    }

    @GetMapping("/wip-details")
    public String wipDetailsPage(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        return "wip-details";
    }

    /**
     * Display commercial dispatch detail page
     */
    @GetMapping("/commercial-dispatch-details")
    public String commercialDispatchDetailsPage(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        return "commercial-dispatch-details";
    }

    /**
     * Success page after registration
     */
    @GetMapping("/success")
    public String success(HttpSession session) {
        // If user is already logged in, redirect to home
        Employee user = (Employee) session.getAttribute("user");
        if (user != null) {
            return "redirect:/home";
        }
        return "success";
    }

    /**
     * Forgot password page
     */
    @GetMapping("/forgot-password")
    public String forgotPassword(HttpSession session) {
        // If user is already logged in, redirect to home
        Employee user = (Employee) session.getAttribute("user");
        if (user != null) {
            return "redirect:/home";
        }
        return "forgot-password";
    }

    /**
     * Handle logout request
     * Invalidates session completely
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate entire session
        if (session != null) {
            session.invalidate();
        }
        // Redirect to logout success page
        return "redirect:/logout-success";
    }
    
    /**
     * Display logout success message
     */
    @GetMapping("/logout-success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "Logout Successfully");
        return "logout-success";
    }

    /**
     * About page - public
     */
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    /**
     * Our Mission page - public
     */
    @GetMapping("/mission")
    public String missionPage() {
        return "mission";
    }

    /**
     * Contact page - public
     */
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    /**
     * Request OTP for password reset
     * - Check if email exists
     * - Generate OTP
     * - Send OTP to email
     * - Redirect to OTP verification page
     */
    @PostMapping("/forgot-password/request-otp")
    public String requestOtp(@RequestParam String emailid, Model model, HttpSession session) {
        emailid = emailid.trim().toLowerCase();

        // Check if user with this email exists
        Employee employee = employeeService.findByEmailid(emailid);
        if (employee == null) {
            logger.warn("Password reset requested for non-existent email: {}", emailid);
            model.addAttribute("error", "Email not found in our system. Please check and try again.");
            return "forgot-password";
        }

        try {
            // Generate OTP
            String otp = otpService.requestOtp(emailid);
            
            // Send OTP via email
            boolean emailSent = emailService.sendOtpEmail(emailid, otp);
            
            if (emailSent) {
                logger.info("OTP requested and sent for email: {}", emailid);
                model.addAttribute("email", emailid);
                model.addAttribute("success", "OTP sent to your email. Please check your inbox.");
                
                // Get remaining seconds for timer
                long remainingSeconds = otpService.getRemainingSeconds(emailid);
                model.addAttribute("remainingSeconds", remainingSeconds);
                
                return "forgot-password-verify";
            } else {
                logger.error("Failed to send OTP email for: {}", emailid);
                model.addAttribute("error", "Failed to send OTP. Please try again.");
                return "forgot-password";
            }
        } catch (Exception e) {
            logger.error("Error requesting OTP for email: {}", emailid, e);
            model.addAttribute("error", "An error occurred. Please try again later.");
            return "forgot-password";
        }
    }

    /**
     * Display OTP verification page
     */
    @GetMapping("/forgot-password/verify")
    public String verifyOtpPage(@RequestParam String email, Model model, HttpSession session) {
        email = email.trim().toLowerCase();
        
        // Check if email exists
        Employee employee = employeeService.findByEmailid(email);
        if (employee == null) {
            return "redirect:/forgot-password";
        }

        // Check if valid OTP exists for this email
        if (!otpService.hasValidOtp(email)) {
            model.addAttribute("error", "OTP expired or not found. Please request a new one.");
            return "forgot-password";
        }

        model.addAttribute("email", email);
        long remainingSeconds = otpService.getRemainingSeconds(email);
        model.addAttribute("remainingSeconds", remainingSeconds);
        
        return "forgot-password-verify";
    }

    /**
     * Verify OTP and proceed to password reset
     */
    @PostMapping("/forgot-password/verify-otp")
    public String verifyOtp(@RequestParam String emailid, 
                            @RequestParam String otp,
                            Model model,
                            HttpSession session) {
        emailid = emailid.trim().toLowerCase();
        otp = otp.trim();

        try {
            // Verify OTP
            if (otpService.verifyOtp(emailid, otp)) {
                logger.info("OTP verified successfully for email: {}", emailid);
                model.addAttribute("email", emailid);
                model.addAttribute("otp", otp);
                model.addAttribute("success", "OTP verified! Now set your new password.");
                return "forgot-password-reset";
            } else {
                logger.warn("Invalid OTP attempt for email: {}", emailid);
                model.addAttribute("email", emailid);
                model.addAttribute("error", "Invalid or expired OTP. Please try again.");
                
                // Get remaining seconds for timer
                long remainingSeconds = otpService.getRemainingSeconds(emailid);
                model.addAttribute("remainingSeconds", remainingSeconds);
                
                return "forgot-password-verify";
            }
        } catch (Exception e) {
            logger.error("Error verifying OTP for email: {}", emailid, e);
            model.addAttribute("email", emailid);
            model.addAttribute("error", "An error occurred. Please try again later.");
            return "forgot-password-verify";
        }
    }

    /**
     * Display password reset page
     */
    @GetMapping("/forgot-password/reset-page")
    public String resetPasswordPage(@RequestParam String email,
                                    @RequestParam String otp,
                                    Model model,
                                    HttpSession session) {
        email = email.trim().toLowerCase();
        otp = otp.trim();

        // Verify that the OTP is still valid
        if (!otpService.verifyOtp(email, otp)) {
            return "redirect:/forgot-password";
        }

        model.addAttribute("email", email);
        model.addAttribute("otp", otp);
        
        return "forgot-password-reset";
    }

    /**
     * Reset password
     * - Verify OTP one more time
     * - Validate password
     * - Update password in database
     * - Mark OTP as used
     * - Send confirmation email
     */
    @PostMapping("/forgot-password/reset")
    public String resetPassword(@RequestParam String emailid,
                               @RequestParam String otp,
                               @RequestParam String createpassword,
                               @RequestParam String confirmpassword,
                               Model model) {
        emailid = emailid.trim().toLowerCase();
        otp = otp.trim();

        // Validate password match
        if (!createpassword.equals(confirmpassword)) {
            model.addAttribute("error", "Passwords do not match. Please try again.");
            model.addAttribute("email", emailid);
            model.addAttribute("otp", otp);
            return "forgot-password-reset";
        }

        // Validate password strength (at least 8 chars with uppercase, lowercase, number, and special char)
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$";
        if (!createpassword.matches(passwordRegex)) {
            model.addAttribute("error", "Password must be at least 8 characters with uppercase, lowercase, number, and special character.");
            model.addAttribute("email", emailid);
            model.addAttribute("otp", otp);
            return "forgot-password-reset";
        }

        try {
            // Verify OTP one last time
            if (!otpService.verifyOtp(emailid, otp)) {
                logger.warn("Invalid OTP in reset attempt for email: {}", emailid);
                model.addAttribute("error", "Invalid or expired OTP. Please request a new one.");
                return "forgot-password";
            }

            // Find employee and update password
            Employee employee = employeeService.findByEmailid(emailid);
            if (employee == null) {
                logger.warn("Employee not found during password reset for email: {}", emailid);
                model.addAttribute("error", "Email not found. Please try again.");
                return "forgot-password";
            }

            // Update password
            employee.setCreatepassword(createpassword);
            employee.setConfirmpassword(confirmpassword);
            employeeService.registerEmployee(employee); // This will update due to same email

            // Mark OTP as used
            otpService.markOtpAsUsed(emailid, otp);

            // Send confirmation email
            emailService.sendPasswordResetConfirmationEmail(emailid, employee.getEmployeename());

            logger.info("Password reset successfully for email: {}", emailid);
            
            // Redirect to login with success message
            return "redirect:/login?success=Password reset successful. Please login with your new password.";

        } catch (Exception e) {
            logger.error("Error resetting password for email: {}", emailid, e);
            model.addAttribute("error", "An error occurred during password reset. Please try again later.");
            model.addAttribute("email", emailid);
            model.addAttribute("otp", otp);
            return "forgot-password-reset";
        }
    }
    
    /**
     * Check if email is available (not registered)
     * Used by JavaScript during registration form input
     */
    @GetMapping("/api/checkEmail")
    @ResponseBody
    public Map<String, Object> checkEmailAvailability(@RequestParam String email) {
        Map<String, Object> result = new HashMap<>();
        email = email.trim().toLowerCase();
        
        if (email.isEmpty()) {
            result.put("available", false);
            result.put("message", "Email cannot be empty");
        } else {
            Employee existing = employeeService.findByEmailid(email);
            if (existing != null) {
                result.put("available", false);
                result.put("exists", true); // indicates email exists in system
                result.put("message", "Email already registered");
            } else {
                result.put("available", true);
                result.put("exists", false);
                result.put("message", "Email is available");
            }
        }
        
        return result;
    }

    /**
     * REST endpoint to get current user details from session
     * Used by JavaScript to populate user info on dashboard
     * Protected by AuthInterceptor
     */
    @GetMapping("/api/userDetails")
    @ResponseBody
    public Map<String, Object> getUserDetails(HttpSession session) {
        Employee user = (Employee) session.getAttribute("user");
        Map<String, Object> result = new HashMap<>();
        
        if (user != null) {
            // Split employee name into first and last name if possible
            String[] names = user.getEmployeename().split(" ", 2);
            result.put("firstName", names.length > 0 ? names[0] : "");
            result.put("lastName", names.length > 1 ? names[1] : "");
            result.put("emailid", user.getEmailid());
            result.put("department", user.getDepartment());
            result.put("employeecontact", user.getEmployeecontact());
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("error", "No user session found");
        }
        
        return result;
    }
}
