package com.example.chefapp.service;

import com.example.chefapp.model.Dish;
import com.example.chefapp.repository.DishRepository;
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

class DishServiceTest {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDish_Success() {
        Dish dish = new Dish();
        dish.setName("Test Dish");
        dish.setDescription("Test Description");
        dish.setPrice(19.99);
        dish.setCategory("Test Category");

        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        Dish savedDish = dishService.saveDish(dish);

        assertNotNull(savedDish);
        assertEquals("Test Dish", savedDish.getName());
        assertEquals(19.99, savedDish.getPrice());
        verify(dishRepository, times(1)).save(any(Dish.class));
    }

    @Test
    void getAllDishes_Success() {
        List<Dish> dishes = Arrays.asList(
            new Dish(), new Dish()
        );

        when(dishRepository.findAll()).thenReturn(dishes);

        List<Dish> result = dishService.getAllDishes();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(dishRepository, times(1)).findAll();
    }

    @Test
    void getDishById_Success() {
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("Test Dish");

        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));

        Dish result = dishService.getDishById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Dish", result.getName());
    }

    @Test
    void getDishById_NotFound() {
        when(dishRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> dishService.getDishById(1L));
    }

    @Test
    void getDishesByChefId_Success() {
        List<Dish> dishes = Arrays.asList(
            new Dish(), new Dish()
        );

        when(dishRepository.findByChefId(1L)).thenReturn(dishes);

        List<Dish> result = dishService.getDishesByChefId(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(dishRepository, times(1)).findByChefId(1L);
    }

    @Test
    void getDishesByPriceRange_Success() {
        List<Dish> dishes = Arrays.asList(
            new Dish(), new Dish()
        );

        when(dishRepository.findByPriceRange(10.0, 30.0)).thenReturn(dishes);

        List<Dish> result = dishService.getDishesByPriceRange(10.0, 30.0);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(dishRepository, times(1)).findByPriceRange(10.0, 30.0);
    }
} 