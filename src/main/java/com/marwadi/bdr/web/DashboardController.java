package com.marwadi.bdr.web;

import com.marwadi.bdr.service.DonorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DonorService donorService;

    public DashboardController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Only logged-in users can see the dashboard
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("totalDonors", donorService.findAll().size());
        return "dashboard";
    }
}