package com.VestaChrono.TestApplication.repository;

import com.VestaChrono.TestApplication.TestContainerConfiguration;
import com.VestaChrono.TestApplication.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    Employee employee = Employee.builder()
            .id(1L)
            .name("Manoj")
            .email("manoj@gmail.com")
            .salary(100L)
            .build();

    @Test
    void testFindByEmail_WhenEmailIsPresent_thenReturnEmployee() {
//        Arrange ,Given
        employeeRepository.save(employee);
//        Act, When
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());
//        Assert, Then
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).isNotEmpty();
        Assertions.assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_WhenEmailIsNotPresent_thenReturnEmptyEmployeeList (){
//        Arrange, Given
        String email = "not.present@gmail.com";
//        Act, When
        List<Employee> employeeList = employeeRepository.findByEmail(email);
//        Assert, Then
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).isEmpty();

    }
}