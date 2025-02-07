package com.revising.apidev.controller;

import com.revising.apidev.dto.EmployeeDTO;
import com.revising.apidev.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {


    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
//        employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Integer id){
        return new ResponseEntity<>(employeeService.getEmployeeByID(id), HttpStatus.CREATED);
    }

    @GetMapping("/employee")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam(name = "id") Integer employeeId){
        return new ResponseEntity<>(employeeService.getEmployeeByID(employeeId), HttpStatus.CREATED);
    }
}
