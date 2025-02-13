package com.thales.thales_api.client.impl;

import com.thales.thales_api.client.EmployeeApiClient;
import com.thales.thales_api.dto.EmployeeDTO;
import com.thales.thales_api.dto.employee.EmployeeApiGeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class EmployeeApiClientImpl implements EmployeeApiClient {

    @Value("${employees.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeApiClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public EmployeeApiGeneralResponse<List<EmployeeDTO>> getAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Cookie", "humans_21909=1");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<EmployeeApiGeneralResponse<List<EmployeeDTO>>> response = restTemplate.exchange(
                apiUrl + "/employees",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<EmployeeApiGeneralResponse<List<EmployeeDTO>>>() {}
        );
        return response.getBody();
    }

    @Override
    public EmployeeApiGeneralResponse<EmployeeDTO> getEmployeeById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Cookie", "humans_21909=1");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<EmployeeApiGeneralResponse<EmployeeDTO>> response = restTemplate.exchange(
                apiUrl + "/employee/" + id,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<EmployeeApiGeneralResponse<EmployeeDTO>>() {}
        );
        return response.getBody();
    }
}
