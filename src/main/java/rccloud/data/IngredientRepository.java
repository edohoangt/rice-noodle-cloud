package rccloud.data;

import org.springframework.data.repository.CrudRepository;

import rccloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	
}
