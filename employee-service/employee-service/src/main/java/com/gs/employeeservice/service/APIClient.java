package com.gs.employeeservice.service;


import com.gs.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//This is used to call any microservices directly without eureka server
//@FeignClient(url = "http://localhost:8080" ,value = "DEPARTMENT-SERVICE")
//@FeignClient(name = "DEPARTMENT-SERVICE")
@FeignClient(url = "http://localhost:8080" ,value = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/departments/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode) ;

}
