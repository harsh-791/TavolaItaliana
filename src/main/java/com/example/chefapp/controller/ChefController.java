package com.example.chefapp.controller;

import com.example.chefapp.model.Chef;
import com.example.chefapp.service.ChefService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping
    public String listChefs(Model model) {
        model.addAttribute("chefs", chefService.getAllChefs());
        return "chef/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("chef", new Chef());
        return "chef/form";
    }

    @PostMapping
    public String createChef(@Valid @ModelAttribute("chef") Chef chef,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "chef/form";
        }
        try {
            chefService.saveChef(chef);
            redirectAttributes.addFlashAttribute("success", "Chef created successfully!");
            return "redirect:/chefs";
        } catch (RuntimeException e) {
            result.rejectValue("email", "error.chef", e.getMessage());
            return "chef/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("chef", chefService.getChefById(id));
        return "chef/form";
    }

    @PostMapping("/{id}")
    public String updateChef(@PathVariable Long id,
                           @Valid @ModelAttribute("chef") Chef chef,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "chef/form";
        }
        try {
            chefService.updateChef(id, chef);
            redirectAttributes.addFlashAttribute("success", "Chef updated successfully!");
            return "redirect:/chefs";
        } catch (RuntimeException e) {
            result.rejectValue("email", "error.chef", e.getMessage());
            return "chef/form";
        }
    }

    @GetMapping("/specialization/{specialization}")
    public String getChefsBySpecialization(@PathVariable String specialization, Model model) {
        model.addAttribute("chefs", chefService.getChefsBySpecialization(specialization));
        return "chef/list";
    }

    @GetMapping("/category/{category}")
    public String getChefsByDishCategory(@PathVariable String category, Model model) {
        model.addAttribute("chefs", chefService.getChefsByDishCategory(category));
        return "chef/list";
    }
} 