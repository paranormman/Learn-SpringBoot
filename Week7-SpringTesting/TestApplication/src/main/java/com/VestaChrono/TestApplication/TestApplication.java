package com.VestaChrono.TestApplication;

import com.VestaChrono.TestApplication.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class TestApplication implements CommandLineRunner {

//	private final DataService dataService;

	@Value("${my.variable}")
	private String myVariable;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("my Variable: " + myVariable);
//		System.out.println("The data is: " + dataService.getData());

	}
}
