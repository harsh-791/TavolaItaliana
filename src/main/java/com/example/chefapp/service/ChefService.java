package com.example.chefapp.service;

import com.example.chefapp.model.Chef;
import com.example.chefapp.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    public Chef saveChef(Chef chef) {
        if (chefRepository.existsByEmail(chef.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return chefRepository.save(chef);
    }

    public List<Chef> getAllChefs() {
        return chefRepository.findAll();
    }

    public Chef getChefById(Long id) {
        return chefRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef not found"));
    }

    public Chef updateChef(Long id, Chef chefDetails) {
        Chef chef = getChefById(id);
        chef.setName(chefDetails.getName());
        chef.setEmail(chefDetails.getEmail());
        chef.setSpecialization(chefDetails.getSpecialization());
        chef.setYearsOfExperience(chefDetails.getYearsOfExperience());
        return chefRepository.save(chef);
    }

    public List<Chef> getChefsBySpecialization(String specialization) {
        return chefRepository.findBySpecialization(specialization);
    }

    public List<Chef> getChefsByDishCategory(String category) {
        return chefRepository.findChefsByDishCategory(category);
    }
} 