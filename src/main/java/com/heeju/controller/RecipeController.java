package com.heeju.controller;

import com.heeju.model.Recipe;
import com.heeju.model.User;
import com.heeju.service.RecipeService;
import com.heeju.service.UserService;
import com.heeju.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private UserService userService;
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }
    @PostMapping("/user/{userID}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userID){
        User user = userService.findUserById(userID);

        Recipe createRecipe = recipeService.createRecipe(recipe, user);
        return createRecipe;
    }

    @PutMapping("/user/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
        Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
        return updatedRecipe;
    }

    @GetMapping()
    public List<Recipe> getAllRecipe() throws Exception {
        List<Recipe> recipes = recipeService.findAllRecipe();
        return recipes;
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return "recipe delete successfully";
    }

    @PutMapping("/{id}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long userId, @PathVariable Long id) throws Exception {
        User user = userService.findUserById(userId);
        Recipe updatedRecipe = recipeService.likeRecipe(id, user);
        return updatedRecipe;
    }
}
