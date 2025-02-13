package com.thales.thales_api.controller;

import com.thales.thales_api.dto.EmployeeDTO;
import com.thales.thales_api.dto.employee.EmployeeApiGeneralResponse;
import com.thales.thales_api.exception.EmployeeApiException;
import com.thales.thales_api.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeApiGeneralResponse<EmployeeDTO> employeeResponse;
    private EmployeeApiGeneralResponse<List<EmployeeDTO>> employeeListResponse;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    public void setUp() {
        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setEmployeeName("Tiger Nixon");
        employeeDTO.setEmployeeSalary(3000L);
        employeeDTO.setEmployeeAge(30L);
        employeeDTO.setProfileImage("");

        employeeResponse = new EmployeeApiGeneralResponse<>();
        employeeResponse.setStatus("success");
        employeeResponse.setData(employeeDTO);
        employeeResponse.setMessage("Successfully! Record has been fetched.");

        employeeListResponse = new EmployeeApiGeneralResponse<>();
        employeeListResponse.setStatus("success");
        employeeListResponse.setData(Collections.singletonList(employeeDTO));
        employeeListResponse.setMessage("Successfully! Record has been fetched.");
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        when(employeeService.getAllEmployees()).thenReturn(employeeListResponse);

        // Act
        ResponseEntity<?> response = employeeController.getAllEmployees();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof EmployeeApiGeneralResponse);
        EmployeeApiGeneralResponse<?> responseBody = (EmployeeApiGeneralResponse<?>) response.getBody();
        assertEquals("success", responseBody.getStatus());
        assertEquals(1, ((List<?>) responseBody.getData()).size());
        verify(employeeService).getAllEmployees();
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        when(employeeService.getEmployeeById(employeeId)).thenReturn(employeeResponse);

        ResponseEntity<?> response = employeeController.getAllEmployeeById(employeeId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof EmployeeApiGeneralResponse);
        EmployeeApiGeneralResponse<?> responseBody = (EmployeeApiGeneralResponse<?>) response.getBody();
        assertEquals("success", responseBody.getStatus());
        assertEquals(employeeDTO.getEmployeeName(), ((EmployeeDTO) responseBody.getData()).getEmployeeName());
        verify(employeeService).getEmployeeById(employeeId);
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        Long employeeId = 2L;
        when(employeeService.getEmployeeById(employeeId)).thenThrow(new EmployeeApiException("Error in employees API"));

        try {
            employeeController.getAllEmployeeById(employeeId);
        } catch (EmployeeApiException e) {
            assertEquals("Error in employees API", e.getMessage());
        }

        verify(employeeService).getEmployeeById(employeeId);
    }
}
