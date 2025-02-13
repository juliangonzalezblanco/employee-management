package com.thales.thales_api.service.impl;

import com.thales.thales_api.client.impl.EmployeeApiClientImpl;
import com.thales.thales_api.dto.EmployeeDTO;
import com.thales.thales_api.dto.employee.EmployeeApiGeneralResponse;
import com.thales.thales_api.exception.EmployeeApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeApiClientImpl employeeApiClient;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeDTO employeeDTO;
    private EmployeeApiGeneralResponse<List<EmployeeDTO>> employeeListResponse;
    private EmployeeApiGeneralResponse<EmployeeDTO> employeeResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setEmployeeName("Tiger Nixon");
        employeeDTO.setEmployeeSalary(3000L);
        employeeDTO.setEmployeeAge(30L);
        employeeDTO.setProfileImage("");

        employeeResponse = new EmployeeApiGeneralResponse<>();
        employeeResponse.setStatus("success");
        employeeResponse.setData(employeeDTO);
        employeeResponse.setMessage("Successfully! All records has been fetched.");

        employeeListResponse = new EmployeeApiGeneralResponse<>();
        employeeListResponse.setStatus("success");
        employeeListResponse.setData(Collections.singletonList(employeeDTO));
        employeeListResponse.setMessage("Successfully! All records has been fetched.");
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeApiClient.getAllEmployees()).thenReturn(employeeListResponse);
        EmployeeApiGeneralResponse<List<EmployeeDTO>> response = employeeService.getAllEmployees();
        assertNotNull(response);
        assertEquals("success", response.getStatus());
        assertEquals(1, response.getData().size());
        assertEquals(36000L, response.getData().get(0).getEmployeeAnualSalary());
        verify(employeeApiClient).getAllEmployees();
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        when(employeeApiClient.getEmployeeById(employeeId)).thenReturn(employeeResponse);
        EmployeeApiGeneralResponse<EmployeeDTO> response = employeeService.getEmployeeById(employeeId);
        assertNotNull(response);
        assertEquals("success", response.getStatus());
        assertEquals("Tiger Nixon", response.getData().getEmployeeName());
        assertEquals(36000L, response.getData().getEmployeeAnualSalary());
        verify(employeeApiClient).getEmployeeById(employeeId);
    }

    @Test
    public void testGetAllEmployees_ClientError() {
        when(employeeApiClient.getAllEmployees()).thenThrow(HttpClientErrorException.class);
        try {
            employeeService.getAllEmployees();
        } catch (EmployeeApiException ex) {
            assertTrue(ex.getMessage().contains("Error in employees API"));
        }
        verify(employeeApiClient).getAllEmployees();
    }

    @Test
    public void testGetEmployeeById_ClientError() {
        Long employeeId = 1L;
        when(employeeApiClient.getEmployeeById(employeeId)).thenThrow(HttpClientErrorException.class);
        try {
            employeeService.getEmployeeById(employeeId);
        } catch (EmployeeApiException ex) {
            assertTrue(ex.getMessage().contains("Error al llamar la API de empleados"));
        }
        verify(employeeApiClient).getEmployeeById(employeeId);
    }

    @Test
    public void testGetAllEmployees_ServerError() {
        when(employeeApiClient.getAllEmployees()).thenThrow(HttpServerErrorException.class);
        try {
            employeeService.getAllEmployees();
        } catch (EmployeeApiException ex) {
            assertTrue(ex.getMessage().contains("Error in employees API"));
        }
        verify(employeeApiClient).getAllEmployees();
    }

    @Test
    public void testGetEmployeeById_ServerError() {
        Long employeeId = 1L;
        when(employeeApiClient.getEmployeeById(employeeId)).thenThrow(HttpServerErrorException.class);
        try {
            employeeService.getEmployeeById(employeeId);
        } catch (EmployeeApiException ex) {
            assertTrue(ex.getMessage().contains("Error al llamar la API de empleados"));
        }
        verify(employeeApiClient).getEmployeeById(employeeId);
    }

    @Test
    public void testGetAllEmployees_UnexpectedError() {
        when(employeeApiClient.getAllEmployees()).thenThrow(new RuntimeException("Unexpected error"));
        try {
            employeeService.getAllEmployees();
        } catch (EmployeeApiException ex) {
            assertTrue(ex.getMessage().contains("Unexpected error in employees API"));
        }
        verify(employeeApiClient).getAllEmployees();
    }

    @Test
    public void testGetEmployeeById_UnexpectedError() {
        Long employeeId = 1L;
        when(employeeApiClient.getEmployeeById(employeeId)).thenThrow(new RuntimeException("Unexpected error in employees API"));
        try {
            employeeService.getEmployeeById(employeeId);
        } catch (EmployeeApiException ex) {
            assertTrue(ex.getMessage().contains("Unexpected error in employees API"));
        }
        verify(employeeApiClient).getEmployeeById(employeeId);
    }
}