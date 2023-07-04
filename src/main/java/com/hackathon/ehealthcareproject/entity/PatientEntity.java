package com.hackathon.ehealthcareproject.entity;

import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import com.hackathon.ehealthcareproject.entity.PrescriptionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient_database")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String specialization;
    private String phoneNumber;
    private boolean isAvailable = true;
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate dateUpdated;
    @OneToMany(mappedBy = "patientEntity", cascade = CascadeType.ALL)
    private List<PrescriptionEntity> prescriptionEntities;
    @OneToMany(mappedBy = "patientEntity", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointmentEntities;
}
