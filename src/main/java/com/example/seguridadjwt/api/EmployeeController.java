package com.example.seguridadjwt.api;

import com.example.seguridadjwt.dao.EmployeeEntity;
import com.example.seguridadjwt.dao.repository.EmployeeRepository;
import com.example.seguridadjwt.dto.EmployeeDto;
import com.example.seguridadjwt.dto.entitityMapper.EmployeeMapper;
import com.example.seguridadjwt.error.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Access;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/protected")
@Validated
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/employees")
    List findAll(){
        return employeeRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping(value = "/employees/{id}")
    ResponseEntity<EmployeeEntity> findById(@PathVariable @Min(1) int id){
        EmployeeEntity emp = employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
        return ResponseEntity.ok().body(emp);

    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping(value = "/employees")
    ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto)
    {
        EmployeeEntity emp = EmployeeMapper.dtoToEntity(employeeDto);
        EmployeeEntity addedEmp = employeeRepository.save(emp);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedEmp.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping(value = "/employee/{id}")
    ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable("id") @Min(1) int id,@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID: " +id));
        EmployeeEntity employeeUpdate = EmployeeMapper.dtoToEntity(employeeDto);
        employeeUpdate.setId(employee.getId());
        employeeRepository.save(employeeUpdate);
        return ResponseEntity.ok().body(employeeUpdate);

    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @DeleteMapping(value = "/employee/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable("id") @Min(1) int id){
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with Id:"+id));
        employeeRepository.deleteById(employee.getId());
        return ResponseEntity.ok().body("Employee deleted with succes");

    }
}
