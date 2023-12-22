package com.gs.departmentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DepartmentDTO {

    private Long id;
    private String departmentName ;
    private String departmentDescription;
    private String departmentCode ;

}
