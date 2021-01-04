package com.keko.code.data;

import org.springframework.data.repository.CrudRepository;

import com.keko.code.Ingredient;

public interface IngredientRepository extends CrudRepository< Ingredient, String >{	
	
}
