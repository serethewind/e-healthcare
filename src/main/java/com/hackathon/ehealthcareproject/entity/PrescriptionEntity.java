package com.hackathon.ehealthcareproject.entity;

import com.hackathon.ehealthcareproject.entity.users.PatientEntity;
import com.hackathon.ehealthcareproject.entity.users.StaffEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prescription_database")
public class PrescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private DoctorEntity doctorEntity;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patientEntity;

    @ManyToOne
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "id")
    private StaffEntity pharmacist;
    private String medicationNames;
    private String dosageInstructions;
    private LocalDate prescriptionStartDate;
    private LocalDate prescriptionEndDate;
}
