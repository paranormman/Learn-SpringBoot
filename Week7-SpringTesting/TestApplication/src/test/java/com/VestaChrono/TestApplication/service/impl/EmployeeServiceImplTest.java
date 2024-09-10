package com.VestaChrono.TestApplication.service.impl;

import com.VestaChrono.TestApplication.TestContainerConfiguration;
import com.VestaChrono.TestApplication.dto.EmployeeDto;
import com.VestaChrono.TestApplication.entity.Employee;
import com.VestaChrono.TestApplication.exception.ResourceNotFoundException;
import com.VestaChrono.TestApplication.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp() {
        mockEmployee = Employee.builder()
                .id(1L)
                .name("Manoj")
                .email("manoj@gmail.com")
                .salary(100L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);
    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdPresent_ThenReturnEmployeeDto() {
        //assign
        Long id = mockEmployee.getId();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee)); //Stubbing

        //act

        EmployeeDto employeeDto = employeeServiceImpl.getEmployeeById(id);

        //assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(mockEmployee.getId());
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        verify(employeeRepository).findById(id);
        verify(employeeRepository, atLeast(1)).findById(id);
        verify(employeeRepository, atMost(2)).findById(id);
    }

    @Test
    void testGetEmployeeById_whenEmployeeIsNotPresent_thenThrowException() {
//        assign
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
//        act and assert

        assertThatThrownBy(() -> employeeServiceImpl.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);
    }

    @Test
    void testCreateNewEmployee_WhenValidEmployee_CreateNewEmployee() {
//        assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

//        act
        EmployeeDto employeeDto = employeeServiceImpl.createEmployee(mockEmployeeDto);

//        assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }

    @Test
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException() {
//        assign
        when(employeeRepository.findByEmail(mockEmployeeDto.getEmail())).thenReturn(List.of(mockEmployee));

//        act and assert
        assertThatThrownBy(() -> employeeServiceImpl.createEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: "+mockEmployee.getEmail());

        verify(employeeRepository).findByEmail(mockEmployeeDto.getEmail());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExists_thenThrowException() {
//        assign
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
//        act and assert
        assertThatThrownBy(() -> employeeServiceImpl.updateEmployee(1L, mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("employee not found with Id: 1");

        verify(employeeRepository).findById(1L);
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException() {
//        assign
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Random");
        mockEmployeeDto.setEmail("random@gmail.com");

//        act and assert
        assertThatThrownBy(() -> employeeServiceImpl.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee can not be updated");

        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository, never()).save(any());

    }

    @Test
    void testUpdateEmployee_whenEmployeeExists_thenUpdateEmployee() {
//        assign
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Random");
        mockEmployeeDto.setSalary(199L);

        Employee newEmployee = modelMapper.map(mockEmployeeDto, Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

//        act
        EmployeeDto updatedEmployeeDto = employeeServiceImpl.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto);

//        assert
        assertThat(updatedEmployeeDto).isNotNull();
        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any());
    }

    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExists_thenReturnException() {
//        assign
        when(employeeRepository.existsById(1L)).thenReturn(false);

//        act and assert
        assertThatThrownBy(() -> employeeServiceImpl.deleteEmployee(mockEmployeeDto.getId()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with Id: " + 1L);

        verify(employeeRepository, never()).deleteById(anyLong());

    }

    @Test
    void testDeleteEmployee_whenEmployeeExists_thenDeleteEmployee() {
//        assign
        when(employeeRepository.existsById(1L)).thenReturn(true);
//        act
//        employeeServiceImpl.deleteEmployee(1L); //not needed, delete method executes as there is no return statement

//        assert
        assertThatCode(() -> employeeServiceImpl.deleteEmployee(1L))
                .doesNotThrowAnyException();

        verify(employeeRepository).deleteById(1L);
    }

}