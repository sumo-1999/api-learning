package com.revising.apidev.service;

import com.revising.apidev.dto.EmployeeDTO;

public interface EmployeeService {

    String createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeByID(Integer id);


}
