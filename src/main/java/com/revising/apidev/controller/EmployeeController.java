package com.revising.apidev.controller;

import com.revising.apidev.dto.EmployeeDTO;
import com.revising.apidev.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*
    * http://localhost:8080/api/v1/employee/
    * body- {
                "name": "krish Doe",
                "email": "krish@example.com",
                "salary": 5000.0,
                "joiningDate": "2025-05-10",
                "department": "IT",
                "phoneNumber": "+91-12345-67890",
                "employeeCode": "EMP-12345"
            }
    *
    * */
    @PostMapping("/employee")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
//        employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
    }

    /*
    * http://localhost:8080/api/v1/employee/2
    * */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Integer id){
        return new ResponseEntity<>(employeeService.getEmployeeByID(id), HttpStatus.CREATED);
    }

//    @GetMapping("/employee")
//    public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam(name = "id") Integer employeeId){
//        return new ResponseEntity<>(employeeService.getEmployeeByID(employeeId), HttpStatus.CREATED);
//    }
}
