package com.example.chefapp.service;

import com.example.chefapp.model.Chef;
import com.example.chefapp.repository.ChefRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChefServiceTest {

    @Mock
    private ChefRepository chefRepository;

    @InjectMocks
    private ChefService chefService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveChef_Success() {
        Chef chef = new Chef();
        chef.setName("Test Chef");
        chef.setEmail("test@example.com");
        chef.setSpecialization("Test Cuisine");
        chef.setYearsOfExperience(10);

        when(chefRepository.existsByEmail(anyString())).thenReturn(false);
        when(chefRepository.save(any(Chef.class))).thenReturn(chef);

        Chef savedChef = chefService.saveChef(chef);

        assertNotNull(savedChef);
        assertEquals("Test Chef", savedChef.getName());
        verify(chefRepository, times(1)).save(any(Chef.class));
    }

    @Test
    void saveChef_DuplicateEmail() {
        Chef chef = new Chef();
        chef.setEmail("existing@example.com");

        when(chefRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> chefService.saveChef(chef));
        verify(chefRepository, never()).save(any(Chef.class));
    }

    @Test
    void getAllChefs_Success() {
        List<Chef> chefs = Arrays.asList(
            new Chef(), new Chef()
        );

        when(chefRepository.findAll()).thenReturn(chefs);

        List<Chef> result = chefService.getAllChefs();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(chefRepository, times(1)).findAll();
    }

    @Test
    void getChefById_Success() {
        Chef chef = new Chef();
        chef.setId(1L);
        chef.setName("Test Chef");

        when(chefRepository.findById(1L)).thenReturn(Optional.of(chef));

        Chef result = chefService.getChefById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Chef", result.getName());
    }

    @Test
    void getChefById_NotFound() {
        when(chefRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> chefService.getChefById(1L));
    }
} 