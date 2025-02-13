package com.thales.thales_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private Long employeeSalary;
    @JsonProperty("employee_age")
    private Long employeeAge;
    @JsonProperty("profile_image")
    private String profileImage;
    @JsonProperty("employee_anual_salary")
    private Long employeeAnualSalary;

}
