package com.heeju.service;

import com.heeju.model.Recipe;
import com.heeju.model.User;
import com.heeju.repository.RecipeRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createRecipe = new Recipe();
        createRecipe.setTitle(recipe.getTitle());
        createRecipe.setUser(user);
        createRecipe.setImage(recipe.getImage());
        createRecipe.setDescription(recipe.getDescription());
        createRecipe.setVegetarian(recipe.isVegetarian());
        createRecipe.setCreatedAt(recipe.getCreatedAt());
        return recipeRepository.save(createRecipe);
    }

    @Override
    public Optional<Recipe> findRecipeById(Long id) {
        Optional<Recipe> opt = recipeRepository.findById(id);
        return opt;
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeById(id);

        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Optional<Recipe> oldRecipeOptional = findRecipeById(id);
        Recipe oldRecipe = oldRecipeOptional.get();

        if(oldRecipeOptional.isPresent()){
            if(recipe.getTitle()!=null){
                oldRecipe.setTitle(recipe.getTitle());
            }
            if(recipe.getImage()!=null){
                oldRecipe.setImage(recipe.getImage());
            }
            if(recipe.getDescription()!=null){
                oldRecipe.setImage(recipe.getDescription());
            }
        }
        return recipeRepository.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) {
        Recipe recipe = findRecipeById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("recipe not found with id"+recipeId));

        if (recipe.getLikes().contains(user.getId())){
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }
}
