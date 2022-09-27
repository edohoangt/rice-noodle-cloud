package rccloud.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import rccloud.AppUser;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String town;
	private String district;
	private String province;
	private String phone;
	
	public AppUser toUser(PasswordEncoder encoder) {
		return new AppUser(username, encoder.encode(password),
				fullname, street, town, district, province, phone);
	}
}
