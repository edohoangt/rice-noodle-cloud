package rccloud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table
public class RiceNoodleOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private Date placedAt = new Date();
	
	@Column("DELIVERY_NAME")
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
