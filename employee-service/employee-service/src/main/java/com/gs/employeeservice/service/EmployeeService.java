package com.gs.employeeservice.service;

import com.gs.employeeservice.dto.APIResponseDTO;
import com.gs.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    public EmployeeDto saveEmployee(EmployeeDto employeeDTO);

    public APIResponseDTO fiendEmployeeById(Long employeeId);
}
