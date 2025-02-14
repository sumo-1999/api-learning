package com.revising.apidev.service.impl;

import com.revising.apidev.dto.EmployeeDTO;
import com.revising.apidev.entity.revision.Employee;
import com.revising.apidev.exception.EmployeeNotFoundException;
import com.revising.apidev.mapper.EmployeeMapper;
import com.revising.apidev.repository.revision.EmployeeRepository;
import com.revising.apidev.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public String createEmployee(EmployeeDTO employeeDTO){
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
        Employee emp =employeeRepository.save(employee);
        return StringUtils.join(emp.getId(), " created");
    }

    @Override
    public EmployeeDTO getEmployeeByID(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("No employee found"));
        return EmployeeMapper.INSTANCE.toDto(employee);
    }

}
