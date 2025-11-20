package com.mealmingle.api.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mealmingle.api.grocery.GroceryItem;
import com.mealmingle.api.recipe.Recipe;
import com.mealmingle.api.repositories.GroceryRepository;
import com.mealmingle.api.repositories.RecipeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grocery")
@RequiredArgsConstructor
@CrossOrigin
public class GroceryController {

    private final GroceryRepository groceryRepo;
    private final RecipeRepository recipeRepo;

    @GetMapping
    public List<GroceryItem> list() {
        return groceryRepo.findAll();
    }

    @PostMapping("/from-recipes")
    public List<GroceryItem> addFromRecipes(@RequestBody List<Long> recipeIds) {
        List<Recipe> recipes = recipeRepo.findAllById(recipeIds);

        Map<String, GroceryItem> map = new LinkedHashMap<>();

        recipes.forEach(r -> r.getIngredients().forEach(ing -> {
            String key = (ing.getIngredientName() + "|" + Objects.toString(ing.getUnit(), "")).toLowerCase();
            map.compute(key, (k, existing) -> {
                double q = Optional.ofNullable(ing.getQuantity()).orElse(0.0);
                if (existing == null) {
                    GroceryItem gi = new GroceryItem();
                    gi.setIngredientName(ing.getIngredientName());
                    gi.setUnit(ing.getUnit());
                    gi.setQuantity(q);
                    return gi;
                }
                existing.setQuantity(existing.getQuantity() + q);
                return existing;
            });
        }));

        groceryRepo.deleteAll();
        return groceryRepo.saveAll(map.values());
    }

    @PatchMapping("/{id}/toggle")
    public GroceryItem toggle(@PathVariable Long id) {
        GroceryItem gi = groceryRepo.findById(id).orElseThrow();
        gi.setPurchased(!gi.isPurchased());
        return groceryRepo.save(gi);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        groceryRepo.deleteById(id);
    }

    @DeleteMapping
    public void clearAll() {
        groceryRepo.deleteAll();
    }
}