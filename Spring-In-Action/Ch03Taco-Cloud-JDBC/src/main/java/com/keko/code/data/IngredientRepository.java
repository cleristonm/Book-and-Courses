package com.keko.code.data;

import com.keko.code.Ingredient;

public interface IngredientRepository {	
	Iterable<Ingredient> findAll();
	
	Ingredient findOne(String id);
	
	Ingredient save(Ingredient ingredient);
}
