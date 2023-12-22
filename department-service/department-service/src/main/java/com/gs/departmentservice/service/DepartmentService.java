package com.gs.departmentservice.service;

import com.gs.departmentservice.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

   public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

   DepartmentDTO getDepartmentByCode(String departmentCode);
}
