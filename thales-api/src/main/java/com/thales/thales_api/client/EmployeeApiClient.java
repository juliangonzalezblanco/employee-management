package com.thales.thales_api.client;

import com.thales.thales_api.dto.EmployeeDTO;
import com.thales.thales_api.dto.employee.EmployeeApiGeneralResponse;

import java.util.List;

public interface EmployeeApiClient {

    EmployeeApiGeneralResponse<List<EmployeeDTO>> getAllEmployees();

    EmployeeApiGeneralResponse<EmployeeDTO> getEmployeeById(Long id);
}
