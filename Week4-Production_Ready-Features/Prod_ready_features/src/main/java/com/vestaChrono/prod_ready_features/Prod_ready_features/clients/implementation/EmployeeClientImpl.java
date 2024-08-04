package com.vestaChrono.prod_ready_features.Prod_ready_features.clients.implementation;

import com.vestaChrono.prod_ready_features.Prod_ready_features.advice.ApiResponse;
import com.vestaChrono.prod_ready_features.Prod_ready_features.clients.EmployeeClient;
import com.vestaChrono.prod_ready_features.Prod_ready_features.dto.EmployeeDTO;
import com.vestaChrono.prod_ready_features.Prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@ToString
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try{
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){
                    });
            return employeeResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
//                    .body(new ParameterizedTypeReference<>()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getBody().getData();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
