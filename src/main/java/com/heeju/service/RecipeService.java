package com.heeju.service;

import com.heeju.model.Recipe;
import com.heeju.model.User;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe, User user);
    Optional<Recipe> findRecipeById(Long id) throws Exception;
    void deleteRecipe(Long id) throws Exception;
    Recipe updateRecipe(Recipe recipe, Long id) throws Exception;
    List<Recipe> findAllRecipe();
    Recipe likeRecipe(Long recipeId, User user) throws Exception;

}
