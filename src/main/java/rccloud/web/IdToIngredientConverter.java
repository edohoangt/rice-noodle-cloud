package rccloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rccloud.Ingredient;
import rccloud.data.IngredientRepository;

@Component
public class IdToIngredientConverter implements Converter<String, Ingredient> {

	private IngredientRepository ingredientRepository;

	@Autowired
	public IdToIngredientConverter(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public Ingredient convert(String id) {
		return ingredientRepository.findById(id).orElse(null);
	}

}
