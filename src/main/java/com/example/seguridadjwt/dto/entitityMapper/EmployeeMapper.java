package com.example.seguridadjwt.dto.entitityMapper;

import com.example.seguridadjwt.dao.EmployeeEntity;
import com.example.seguridadjwt.dto.EmployeeDto;

public class EmployeeMapper {
    public static EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employeeEntity.getFirstName());
        employeeDto.setLastName(employeeEntity.getLastName());
        employeeDto.setJob(employeeEntity.getJob());
        employeeDto.setSalary(employeeEntity.getSalary());
        return employeeDto;
    }
    public static EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setJob(employeeDto.getJob());
        employeeEntity.setSalary(employeeDto.getSalary());
        return employeeEntity;
    }
}

