package com.example.chefapp.repository;

import com.example.chefapp.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChefRepository extends JpaRepository<Chef, Long> {
    
    @Query("SELECT c FROM Chef c WHERE c.specialization = ?1")
    List<Chef> findBySpecialization(String specialization);
    
    @Query("SELECT c FROM Chef c JOIN c.dishes d WHERE d.category = ?1")
    List<Chef> findChefsByDishCategory(String category);
    
    boolean existsByEmail(String email);
} 