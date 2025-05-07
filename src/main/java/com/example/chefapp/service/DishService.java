package com.example.chefapp.service;

import com.example.chefapp.model.Dish;
import com.example.chefapp.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    public Dish updateDish(Long id, Dish dishDetails) {
        Dish dish = getDishById(id);
        dish.setName(dishDetails.getName());
        dish.setDescription(dishDetails.getDescription());
        dish.setPrice(dishDetails.getPrice());
        dish.setCategory(dishDetails.getCategory());
        return dishRepository.save(dish);
    }

    public List<Dish> getDishesByChefId(Long chefId) {
        return dishRepository.findByChefId(chefId);
    }

    public List<Dish> getDishesByPriceRange(Double minPrice, Double maxPrice) {
        return dishRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Dish> getDishesByChefSpecialization(String specialization) {
        return dishRepository.findByChefSpecialization(specialization);
    }
} 