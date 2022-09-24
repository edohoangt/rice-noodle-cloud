package rccloud;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class RiceNoodleOrder {
	
	@NotBlank(message = "Delivery name is required.") // trimmed value cannot be empty
	private String deliveryName;
	
	@NotBlank(message = "Street is required.")
	private String deliveryStreet;
	
	@NotBlank(message = "Town is required.")
	private String deliveryTown;
	
	@NotBlank(message = "District is required.")
	private String deliveryDistrict;
	
	@NotBlank(message = "Province is required.")
	private String deliveryProvince;
	
	@CreditCardNumber(message = "Not a valid credit card number.")
	private String ccNumber;
	
	@Pattern(regexp = "^(0[1-9]|1[0-2])(\\/)([2-9][0-9])$", message = "Must be formatted MM/YY.")
	private String ccExpiration;
	
	@Digits(integer = 3, fraction = 0, message = "Invalid CVV.")
	private String ccCVV;
	
	private List<RiceNoodle> riceNoodles = new ArrayList<>();
	
	public void addRiceNoodle(RiceNoodle rn) {
		this.riceNoodles.add(rn);
	}
	
}