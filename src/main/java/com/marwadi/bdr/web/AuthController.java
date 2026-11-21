package com.marwadi.bdr.web;

import com.marwadi.bdr.entity.AppUser;
import com.marwadi.bdr.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("loggedUser") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam(defaultValue = "") String username,
                          @RequestParam(defaultValue = "") String password,
                          HttpSession session, Model model) {
        String u = username.trim();

        // Server-side validation
        if (u.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Username and password are required.");
            model.addAttribute("username", u);
            return "login";
        }

        Optional<AppUser> user = authService.login(u, password);
        if (user.isEmpty()) {
            model.addAttribute("error", "Wrong username or password.");
            model.addAttribute("username", u);
            return "login";
        }

        // Save the logged-in user in the session
        session.setAttribute("loggedUser", user.get().getUsername());
        session.setAttribute("loggedFullName", user.get().getFullName());
        session.setAttribute("loggedRole", user.get().getRole());
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}