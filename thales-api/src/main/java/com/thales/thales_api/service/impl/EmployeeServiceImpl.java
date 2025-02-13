package com.thales.thales_api.service.impl;

import com.thales.thales_api.client.impl.EmployeeApiClientImpl;
import com.thales.thales_api.dto.EmployeeDTO;
import com.thales.thales_api.dto.employee.EmployeeApiGeneralResponse;
import com.thales.thales_api.exception.EmployeeApiException;
import com.thales.thales_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeApiClientImpl employeeApiClient;

    @Override
    public EmployeeApiGeneralResponse<List<EmployeeDTO>> getAllEmployees() {
        try {
            EmployeeApiGeneralResponse<List<EmployeeDTO>> listEmployeeApiGeneralResponse = employeeApiClient.getAllEmployees();
            listEmployeeApiGeneralResponse.getData().forEach((e)->e.setEmployeeAnualSalary(e.getEmployeeSalary()*12));
            return listEmployeeApiGeneralResponse;
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new EmployeeApiException("Error al llamar la API de empleados: " + ex.getMessage());
        } catch (Exception ex) {
            throw new EmployeeApiException("Error inesperado al obtener empleados: " + ex.getMessage());
        }
    }

    @Override
    public EmployeeApiGeneralResponse<EmployeeDTO> getEmployeeById(Long id) {
        try {
            EmployeeApiGeneralResponse<EmployeeDTO> employeeApiGeneralResponse = employeeApiClient.getEmployeeById(id);
            employeeApiGeneralResponse.getData().setEmployeeAnualSalary(employeeApiGeneralResponse.getData().getEmployeeSalary()*12);
            return employeeApiGeneralResponse;
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new EmployeeApiException("Error al llamar la API de empleados: " + ex.getMessage());
        } catch (Exception ex) {
            throw new EmployeeApiException("Error inesperado al obtener empleados: " + ex.getMessage());
        }
    }
}
