package rccloud.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rccloud.data.IngredientRepository;
import rccloud.Ingredient;
import rccloud.Ingredient.Type;

@Configuration
public class DataConfig {
	
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return args -> {
			repo.save(new Ingredient("FULL", "Day du", Type.VEGGIES));
			repo.save(new Ingredient("VEOL", "Chi rau song", Type.VEGGIES));
			repo.save(new Ingredient("SPOL", "Chi gia", Type.VEGGIES));
			repo.save(new Ingredient("BEEF", "Thit bo", Type.MEAT));
			repo.save(new Ingredient("PORK", "Thit heo", Type.MEAT));
			repo.save(new Ingredient("PSFH", "Cha ca", Type.PASTE));
			repo.save(new Ingredient("PSBF", "Cha bo", Type.PASTE));
			repo.save(new Ingredient("PSPK", "Cha heo", Type.PASTE));
			repo.save(new Ingredient("PSCR", "Cha cua", Type.PASTE));
			repo.save(new Ingredient("SLNO", "Khong cay", Type.SPICY_LEVEL));
			repo.save(new Ingredient("SLMD", "Cay vua", Type.SPICY_LEVEL));
			repo.save(new Ingredient("SLEX", "Cay manh", Type.SPICY_LEVEL));
			repo.save(new Ingredient("NTBU", "Soi bun", Type.NOODLE_TYPE));
			repo.save(new Ingredient("NTPH", "Soi pho", Type.NOODLE_TYPE));
		};
	}

}
