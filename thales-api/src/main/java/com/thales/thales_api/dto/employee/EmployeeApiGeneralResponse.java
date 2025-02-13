package com.thales.thales_api.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeApiGeneralResponse <T>{

    private String status;
    @JsonProperty("data")
    private T data;
    private String message;
}
