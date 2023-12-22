package com.gs.employeeservice.service.impl;

import com.gs.employeeservice.dto.APIResponseDTO;
import com.gs.employeeservice.dto.DepartmentDto;
import com.gs.employeeservice.dto.EmployeeDto;
import com.gs.employeeservice.entity.Employee;
import com.gs.employeeservice.repository.EmployeeRepository;
import com.gs.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;


@Service(value = "EmployeeService")
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {



    @Autowired(required = true)
    EmployeeRepository employeeRepository;
    @Autowired(required = true)
    @Qualifier("modelMapper")
    ModelMapper modelMapper;

 //   RestTemplate restTemplate;

    WebClient webClient;

 //   APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDTO) {

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployees = employeeRepository.save(employee);
        return modelMapper.map(savedEmployees,EmployeeDto.class);

    }
    @Override
    //@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    public APIResponseDTO fiendEmployeeById(Long employeeId) {

        System.out.println("inside fiendEmployeeById()");

        Employee savedEmployee = employeeRepository.findById(employeeId).get();

  /*
  DepartmentDto departmentDto = restTemplate.getForEntity("http://localhost:8080/api/department/"
                        + savedEmployee.getDepartmentCode(),
                DepartmentDto.class).getBody();

       */



        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + savedEmployee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
     //   DepartmentDto departmentDto= apiClient.getDepartmentByCode(savedEmployee.getDepartmentCode()).getBody();

        EmployeeDto employeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDto(employeeDto);
        apiResponseDTO.setDepartmentDto(departmentDto);
        return apiResponseDTO;
    }

    public APIResponseDTO getDefaultDepartment(Long employeeId, Exception exception) {

        System.out.println("inside getDefaultDepartment()");
        Employee savedEmployee = employeeRepository.findById(employeeId).get();
        EmployeeDto employeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployeeDto(employeeDto);
        DepartmentDto departmentDto = new DepartmentDto(null,"R&D Department","RD001","Natural Language Process");
        apiResponseDTO.setDepartmentDto(departmentDto);
        return apiResponseDTO;
    }
}
