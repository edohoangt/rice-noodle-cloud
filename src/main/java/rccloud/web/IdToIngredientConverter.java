package rccloud.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rccloud.Ingredient;
import rccloud.Ingredient.Type;

@Component
public class IdToIngredientConverter implements Converter<String, Ingredient> {
	
	private Map<String, Ingredient> ingredientMap = new HashMap<>();

	public IdToIngredientConverter() {
		ingredientMap.put("FULL", new Ingredient("FULL", "Day du", Type.VEGGIES));
		ingredientMap.put("SPOL", new Ingredient("SPOL", "Chi gia", Type.VEGGIES));
		ingredientMap.put("VEOL", new Ingredient("VEOL", "Chi rau song", Type.VEGGIES));
		ingredientMap.put("BEEF", new Ingredient("BEEF", "Thit bo", Type.MEAT));
		ingredientMap.put("PORL", new Ingredient("PORK", "Thit heo", Type.MEAT));
		ingredientMap.put("PSFH", new Ingredient("PSFH", "Cha ca", Type.PASTE));
		ingredientMap.put("PSBF", new Ingredient("PSBF", "Cha bo", Type.PASTE));
		ingredientMap.put("PSPK", new Ingredient("PSPK", "Cha heo", Type.PASTE));
		ingredientMap.put("PSCR", new Ingredient("PSCR", "Cha cua", Type.PASTE));
		ingredientMap.put("SLNO", new Ingredient("SLNO", "Khong cay", Type.SPICY_LEVEL));
		ingredientMap.put("SLMD", new Ingredient("SLMD", "Cay vua", Type.SPICY_LEVEL));
		ingredientMap.put("SLEX", new Ingredient("SLEX", "Cay manh", Type.SPICY_LEVEL));
		ingredientMap.put("NTBU", new Ingredient("NTBU", "Soi bun", Type.NOODLE_TYPE));
		ingredientMap.put("NTPH", new Ingredient("NTPH", "Soi pho", Type.NOODLE_TYPE));
	}

	@Override
	public Ingredient convert(String id) {
		return ingredientMap.get(id);
	}
	
}
