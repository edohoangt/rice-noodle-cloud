package rccloud;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("INGREDIENT_REF")
public class IngredientRef {
	
	private final String ingredient;
	
}
