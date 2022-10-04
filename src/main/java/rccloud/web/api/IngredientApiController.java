package rccloud.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rccloud.Ingredient;
import rccloud.data.IngredientRepository;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
public class IngredientApiController {

	private IngredientRepository repository;
	
	@Autowired
	public IngredientApiController(IngredientRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		return repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
		return repository.save(ingredient);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIngredient(@PathVariable String ingredientId) {
		repository.deleteById(ingredientId);
	}
	
}
