package com.gs.departmentservice.service.imp;

import com.gs.departmentservice.dto.DepartmentDTO;
import com.gs.departmentservice.entity.Department;
import com.gs.departmentservice.repository.DepartmentRepository;
import com.gs.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier(value="departmentService")
@AllArgsConstructor
public class DepartmentServiceImp implements DepartmentService {


    private ModelMapper modelMapper;
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        //convert department dto to department entity
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department saveedDepartment = departmentRepository.save(department);
        return modelMapper.map(saveedDepartment,DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        return modelMapper.map(department,DepartmentDTO.class);
    }
}
