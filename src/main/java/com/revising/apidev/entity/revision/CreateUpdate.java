package com.revising.apidev.entity.revision;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class CreateUpdate {

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdDateTime;
    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedDateTime;
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

}
