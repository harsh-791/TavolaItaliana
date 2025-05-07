package com.example.chefapp.controller;

import com.example.chefapp.model.Dish;
import com.example.chefapp.service.DishService;
import com.example.chefapp.service.ChefService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private ChefService chefService;

    @GetMapping
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        return "dish/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", chefService.getAllChefs());
        return "dish/form";
    }

    @PostMapping
    public String createDish(@Valid @ModelAttribute("dish") Dish dish,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "dish/form";
        }
        dishService.saveDish(dish);
        redirectAttributes.addFlashAttribute("success", "Dish created successfully!");
        return "redirect:/dishes";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("dish", dishService.getDishById(id));
        model.addAttribute("chefs", chefService.getAllChefs());
        return "dish/form";
    }

    @PostMapping("/{id}")
    public String updateDish(@PathVariable Long id,
                           @Valid @ModelAttribute("dish") Dish dish,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "dish/form";
        }
        dishService.updateDish(id, dish);
        redirectAttributes.addFlashAttribute("success", "Dish updated successfully!");
        return "redirect:/dishes";
    }

    @GetMapping("/chef/{chefId}")
    public String getDishesByChef(@PathVariable Long chefId, Model model) {
        model.addAttribute("dishes", dishService.getDishesByChefId(chefId));
        return "dish/list";
    }

    @GetMapping("/price-range")
    public String getDishesByPriceRange(@RequestParam Double minPrice,
                                      @RequestParam Double maxPrice,
                                      Model model) {
        model.addAttribute("dishes", dishService.getDishesByPriceRange(minPrice, maxPrice));
        return "dish/list";
    }

    @GetMapping("/specialization/{specialization}")
    public String getDishesByChefSpecialization(@PathVariable String specialization, Model model) {
        model.addAttribute("dishes", dishService.getDishesByChefSpecialization(specialization));
        return "dish/list";
    }
} 