package com.gs.departmentservice.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId", nullable = false)
    private Long id;
    private String departmentName;
    private String departmentDescription;
    @Column(unique = true)
    private String departmentCode ;

}
