package com.thales.thales_api.client.impl;

import com.thales.thales_api.client.EmployeeApiClient;
import com.thales.thales_api.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
    public List<EmployeeDTO> getAllEmployees() {
        ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDTO>>() {}
        );
        return response.getBody();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        String url = apiUrl + "/" + id;
        return restTemplate.getForObject(url, EmployeeDTO.class);
    }
}
