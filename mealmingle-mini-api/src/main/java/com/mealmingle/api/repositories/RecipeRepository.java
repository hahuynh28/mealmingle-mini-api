package com.mealmingle.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mealmingle.api.recipe.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
