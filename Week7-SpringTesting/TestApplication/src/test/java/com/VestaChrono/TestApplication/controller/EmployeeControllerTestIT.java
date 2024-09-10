package com.VestaChrono.TestApplication.controller;

import com.VestaChrono.TestApplication.TestContainerConfiguration;
import com.VestaChrono.TestApplication.dto.EmployeeDto;
import com.VestaChrono.TestApplication.entity.Employee;
import com.VestaChrono.TestApplication.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
class EmployeeControllerTestIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDto testEmployeeDto;

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = Employee.builder()
                .id(1L)
                .name("Manoj")
                .email("manoj@gmail.com")
                .salary(199L)
                .build();

        testEmployeeDto = EmployeeDto.builder()
                .id(1L)
                .name("Manoj")
                .email("manoj@gmail.com")
                .salary(199L)
                .build();

        employeeRepository.deleteAll();
    }

    @Test
    void testGetEmployeeById_success() {
        Employee savedEmployee = employeeRepository.save(testEmployee);
        webTestClient.get()
                .uri("/employees/{id}", savedEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .isEqualTo(testEmployeeDto);
//                .value(employeeDto -> {
//                    assertThat(employeeDto.getId()).isEqualTo(savedEmployee.getId());
//                    assertThat(employeeDto.getEmail()).isEqualTo(savedEmployee.getEmail());
//                });

    }

    @Test
    void testGetEmployeeById_failure() {
        webTestClient.get()
                .uri("/employees/1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateNewEmployee_whenEmployeeAlreadyExists_thenThrowException() {
        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.post()
                .uri("/employees")
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}