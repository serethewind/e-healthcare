package com.hackathon.ehealthcareproject.entity.users;

import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import com.hackathon.ehealthcareproject.entity.PrescriptionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;
    @OneToMany(mappedBy = "patientEntity", cascade = CascadeType.ALL)
    private List<PrescriptionEntity> prescriptionEntities;
    @OneToMany(mappedBy = "patientEntity", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointmentEntities;
}
