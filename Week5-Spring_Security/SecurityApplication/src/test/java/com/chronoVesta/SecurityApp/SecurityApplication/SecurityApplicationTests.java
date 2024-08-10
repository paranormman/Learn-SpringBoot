package com.chronoVesta.SecurityApp.SecurityApplication;

import com.chronoVesta.SecurityApp.SecurityApplication.entity.User;
import com.chronoVesta.SecurityApp.SecurityApplication.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User(4L, "manoj@gmail.com", "123456", "manoj");

		String token = jwtService.generateToken(user);
		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);
		System.out.println(id);

	}

}
