package com.hackathon.ehealthcareproject.entity;

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
@Table(name = "appointment_database")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate appointmentDate;
    @ManyToOne
    @JoinColumn(name = "patient_entity_id", referencedColumnName = "id")
    private PatientEntity patientEntity;
    @ManyToOne
    @JoinColumn(name = "doctor_entity_id", referencedColumnName = "id")
    private DoctorEntity doctorEntity;
}
