package com.mealmingle.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mealmingle.api.recipe.Recipe;
import com.mealmingle.api.repositories.RecipeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@CrossOrigin // so Angular can call it during dev
public class RecipeController {

    private final RecipeRepository repo;

    @GetMapping
    public List<Recipe> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Recipe one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe r) {
        if (r.getIngredients() != null) {
            r.getIngredients().forEach(i -> i.setRecipe(r));
        }
        return repo.save(r);
    }

    @PutMapping("/{id}")
    public Recipe update(@PathVariable Long id, @RequestBody Recipe r) {
        r.setId(id);
        if (r.getIngredients() != null) {
            r.getIngredients().forEach(i -> i.setRecipe(r));
        }
        return repo.save(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}