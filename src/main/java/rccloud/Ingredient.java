package rccloud;

public class Ingredient {
		
	public Ingredient(String id, String name, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	private final String id;
	
	private final String name;
	
	private final Type type;
	
	public enum Type {
		VEGGIES, MEAT, PASTE, SPICY_LEVEL, NOODLE_TYPE
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
	
	
}
