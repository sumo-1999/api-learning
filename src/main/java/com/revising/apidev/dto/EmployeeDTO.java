package com.revising.apidev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDTO {
    @NotBlank(message = "{name.notblank}")
    @Size(min = 2, max = 50, message = "{name.size}")
    private String name;

    @NotBlank(message = "{email.notblank}")
    @Email(message = "{email.invalid}")
    private String email;

    @Min(value = 3000, message = "{salary.min}")
    private double salary;

    @FutureOrPresent(message = "{joiningDate.futureOrPresent}") // No past dates
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Accepts date as "yyyy-MM-dd"
    private LocalDate joiningDate;

    @NotNull(message = "{department.notnull}")
    private Department department;

    @Pattern(regexp = "\\+91-\\d{5}-\\d{5}", message = "{phone.invalid}") // Enforce phone format "+91-XXXXX-XXXXX"
    private String phoneNumber;

    @Pattern(regexp = "EMP-\\d{5}", message = "{employeeCode.invalid}") // Enforce employee code format "EMP-XXXXX"
    private String employeeCode;
}
