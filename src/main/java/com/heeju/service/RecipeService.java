package com.heeju.service;

import com.heeju.model.Recipe;
import com.heeju.model.User;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    public Recipe createRecipe(Recipe recipe, User user);
    public Optional<Recipe> findRecipeById(Long id) throws Exception;
    public void deleteRecipe(Long id) throws Exception;
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;
    public List<Recipe> findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;

}
