package rccloud;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RiceNoodle {
	
	@NotNull // can be empty string
	@Size(min = 5, message = "Name must be at least 5 characters long.")
	private String name;
	
	@NotNull
	@Size(min = 1, message = "You must choose at least 1 ingredient.")
	private List<Ingredient> ingredients;
	
	public RiceNoodle() {}

	public RiceNoodle(String name, List<Ingredient> ingredients) {
		super();
		this.name = name;
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Taco [name=" + name + ", ingredients=" + ingredients + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}
