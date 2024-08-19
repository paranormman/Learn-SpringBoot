package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.User;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class Week5SpringSecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User(4L, "manoj@gmail.com", "manoj", "123456");

		String token = jwtService.generateToken(user);
		System.out.println(token);

		Long Id = jwtService.getUserIdFromToken(token);
		System.out.println(Id);

//		get all details in the payload
		Claims claims = jwtService.getAllClaimsFromToken(token);
		for (Map.Entry<String, Object> entry : claims.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

	}

}
