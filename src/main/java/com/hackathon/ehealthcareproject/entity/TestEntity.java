package com.hackathon.ehealthcareproject.entity;

import com.hackathon.ehealthcareproject.entity.users.PatientEntity;
import com.hackathon.ehealthcareproject.entity.users.StaffEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_database")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String testName;
    private String testDescription;
    private LocalDate testDate;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patientEntity;
    @ManyToOne
    @JoinColumn(name = "medical_technologist_id", referencedColumnName = "id")
    private StaffEntity medicalTechnologist;
    private String result;
    private LocalDateTime resultDate;
}
