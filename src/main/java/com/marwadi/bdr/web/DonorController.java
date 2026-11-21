package com.marwadi.bdr.web;

import com.marwadi.bdr.entity.Donor;
import com.marwadi.bdr.repository.BloodBankRepository;
import com.marwadi.bdr.service.DonorService;
import com.marwadi.bdr.web.form.DonorForm;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/donors")
public class DonorController {

    private final DonorService donorService;
    private final BloodBankRepository bloodBankRepository;

    public DonorController(DonorService donorService, BloodBankRepository bloodBankRepository) {
        this.donorService = donorService;
        this.bloodBankRepository = bloodBankRepository;
    }

    // READ: list all donors
    @GetMapping
    public String list(Model model) {
        model.addAttribute("donors", donorService.findAll());
        return "donor-list";
    }

    // RADAR SEARCH page
    @GetMapping("/search")
    public String searchPage(@RequestParam(required = false) String bloodGroup,
                             @RequestParam(required = false) String city,
                             Model model) {
        String bg = (bloodGroup == null || bloodGroup.isBlank()) ? null : bloodGroup.trim();
        String c = (city == null || city.isBlank()) ? null : city.trim();

        model.addAttribute("bloodGroups", DonorForm.BLOOD_GROUPS);
        model.addAttribute("selectedBloodGroup", bg == null ? "" : bg);
        model.addAttribute("selectedCity", c == null ? "" : c);

        if (bg != null || c != null) {
            model.addAttribute("results", donorService.search(bg, c));
        }
        return "search";
    }

    // CREATE: show empty form
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("donorForm", new DonorForm());
        model.addAttribute("formTitle", "Add Donor");
        addLookups(model);
        return "donor-form";
    }

    // CREATE: save the new donor
    @PostMapping("/new")
    public String addSave(@Valid @ModelAttribute("donorForm") DonorForm form,
                          BindingResult result,
                          Model model,
                          RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Add Donor");
            addLookups(model);
            return "donor-form";
        }
        donorService.save(form);
        ra.addFlashAttribute("success", "Donor added successfully.");
        return "redirect:/donors";
    }

    // UPDATE: show form filled with the donor's data
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Donor donor = donorService.findById(id);
        if (donor == null) {
            ra.addFlashAttribute("error", "Donor not found.");
            return "redirect:/donors";
        }
        model.addAttribute("donorForm", DonorForm.from(donor));
        model.addAttribute("formTitle", "Edit Donor");
        addLookups(model);
        return "donor-form";
    }

    // UPDATE: save the changes
    @PostMapping("/{id}/edit")
    public String editSave(@PathVariable Integer id,
                           @Valid @ModelAttribute("donorForm") DonorForm form,
                           BindingResult result,
                           Model model,
                           RedirectAttributes ra) {
        form.setDonorId(id);
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Edit Donor");
            addLookups(model);
            return "donor-form";
        }
        donorService.save(form);
        ra.addFlashAttribute("success", "Donor updated successfully.");
        return "redirect:/donors";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            donorService.delete(id);
            ra.addFlashAttribute("success", "Donor deleted.");
        } catch (DataAccessException e) {
            ra.addFlashAttribute("error",
                    "Cannot delete this donor because donation records exist for them.");
        }
        return "redirect:/donors";
    }

    // Dropdown values used by the form
    private void addLookups(Model model) {
        model.addAttribute("bloodGroups", DonorForm.BLOOD_GROUPS);
        model.addAttribute("statuses", DonorForm.STATUSES);
        model.addAttribute("banks", bloodBankRepository.findAll());
    }
}