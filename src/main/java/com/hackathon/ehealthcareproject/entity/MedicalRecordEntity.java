package com.hackathon.ehealthcareproject.entity;

import com.hackathon.ehealthcareproject.entity.users.PatientEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medical_records_database")
public class MedicalRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_entity_id", referencedColumnName = "id")
    private PatientEntity patientEntity;
    private String medicalHistory;
    private String otherNotes;

}
