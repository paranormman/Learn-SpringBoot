package com.vestaChrono.prod_ready_features.Prod_ready_features.clients.implementation;

import com.vestaChrono.prod_ready_features.Prod_ready_features.advice.ApiResponse;
import com.vestaChrono.prod_ready_features.Prod_ready_features.clients.EmployeeClient;
import com.vestaChrono.prod_ready_features.Prod_ready_features.dto.EmployeeDTO;
import com.vestaChrono.prod_ready_features.Prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to retrieve all employee data from getAllEmployees");
        try{
            log.info("Attempting to call the restClient Method in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not get the employee details");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved All employees from getAllEmployees");
            log.trace("Retrieved employees List in getAllEmployees: {}", employeeDTOList.getData());
            return employeeDTOList.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            log.trace("Trying to retrieve Employee by Id in getEmployeeById with Id: {}", employeeId);
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not get the employee details");
                    })
                    .body(new ParameterizedTypeReference<>(){
                    });
            return employeeResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getEmployeeById", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            log.trace("Creating new Employee from createNewEmployee with details {}", employeeDTO);
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xxClient error occurred during createNewEmployee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });

            log.trace("Successfully created a new Employee: {}", employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();
        }catch (Exception e) {
            log.error("Exception occurred in createNewEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
