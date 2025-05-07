package com.example.chefapp.repository;

import com.example.chefapp.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    
    List<Dish> findByChefId(Long chefId);
    
    @Query("SELECT d FROM Dish d WHERE d.price BETWEEN ?1 AND ?2")
    List<Dish> findByPriceRange(Double minPrice, Double maxPrice);
    
    @Query("SELECT d FROM Dish d JOIN d.chef c WHERE c.specialization = ?1")
    List<Dish> findByChefSpecialization(String specialization);
} 