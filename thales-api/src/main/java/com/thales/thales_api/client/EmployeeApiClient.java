package com.thales.thales_api.client;

import com.thales.thales_api.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeApiClient {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);
}
