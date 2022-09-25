package rccloud.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import rccloud.Ingredient;
import rccloud.Ingredient.Type;
import rccloud.RiceNoodle;
import rccloud.RiceNoodleOrder;
import rccloud.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/create")
@SessionAttributes("riceNoodleOrder")
public class CustomizeNoodleController {
	
	private final IngredientRepository ingredientRepository;
	
	@Autowired
	public CustomizeNoodleController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	private Iterable<Ingredient> filterIngredientsByType(Iterable<Ingredient> ingredients, Type type) {
		return StreamSupport.stream(ingredients.spliterator(), false)
				.filter(ig -> ig.getType().equals(type))
				.collect(Collectors.toList());
	}
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(
//			new Ingredient("FULL", "Day du", Type.VEGGIES),
//			new Ingredient("VEOL", "Chi rau song", Type.VEGGIES),
//			new Ingredient("SPOL", "Chi gia", Type.VEGGIES),
//			new Ingredient("BEEF", "Thit bo", Type.MEAT),
//			new Ingredient("PORK", "Thit heo", Type.MEAT),
//			new Ingredient("PSFH", "Cha ca", Type.PASTE),
//			new Ingredient("PSBF", "Cha bo", Type.PASTE),
//			new Ingredient("PSPK", "Cha heo", Type.PASTE),
//			new Ingredient("PSCR", "Cha cua", Type.PASTE),
//			new Ingredient("SLNO", "Khong cay", Type.SPICY_LEVEL),
//			new Ingredient("SLMD", "Cay vua", Type.SPICY_LEVEL),
//			new Ingredient("SLEX", "Cay manh", Type.SPICY_LEVEL),
//			new Ingredient("NTBU", "Soi bun", Type.NOODLE_TYPE),
//			new Ingredient("NTPH", "Soi pho", Type.NOODLE_TYPE)
//		);
		
		Iterable<Ingredient> ingredients = ingredientRepository.findAll();
		
		Type[] types = Ingredient.Type.values();
		
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterIngredientsByType(ingredients, type));
		}
	}
	
	@ModelAttribute(name = "riceNoodleOrder")
	public RiceNoodleOrder riceNoodleOrder() {
		return new RiceNoodleOrder();
	}
	
	@ModelAttribute(name = "riceNoodle")
	public RiceNoodle riceNoodle() {
		return new RiceNoodle();
	}
	
	@GetMapping
	public String showRiceNoodleCreateForm() {
		return "create";
	}
	
	@PostMapping
	public String processRiceNoodle(@ModelAttribute RiceNoodleOrder riceNoodleOrder, @Valid RiceNoodle riceNoodle, Errors errors) {
		if (errors.hasErrors()) {
			return "create";
		}
		
		riceNoodleOrder.addRiceNoodle(riceNoodle);
		log.info("Processing noodle: {}", riceNoodle);
		
		return "redirect:/orders/current";
	}
	
}
