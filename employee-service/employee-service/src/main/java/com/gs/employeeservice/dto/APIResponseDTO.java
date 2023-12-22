package com.gs.employeeservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class APIResponseDTO {
    private EmployeeDto employeeDto;
   private DepartmentDto departmentDto;

}
