package com.revising.apidev.mapper;

import com.revising.apidev.dto.EmployeeDTO;
import com.revising.apidev.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

        EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

        EmployeeDTO toDto(Employee employee);
        Employee toEntity(EmployeeDTO employeeDTO);

}
