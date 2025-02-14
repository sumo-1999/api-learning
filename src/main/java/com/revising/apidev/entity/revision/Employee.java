package com.revising.apidev.entity.revision;

import com.revising.apidev.entity.revision.CreateUpdate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Employee extends CreateUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private Double salary;

    private LocalDate joiningDate;

    private  String department;

    private String employeeCode;

    private String phoneNumber;

    @PrePersist
    public void prePersistUpdateDateTime() {
        if (null == super.getUpdatedDateTime()) {
            super.setUpdatedDateTime(LocalDateTime.now());
        }
        if (null == super.getCreatedDateTime()) {
            super.setCreatedDateTime(LocalDateTime.now());
        }

        if (null == super.getCreatedBy()) {
            super.setCreatedBy("SYSTEM");
        }
        if (null == super.getUpdatedBy()) {
            super.setUpdatedBy("SYSTEM");
        }

    }
}

