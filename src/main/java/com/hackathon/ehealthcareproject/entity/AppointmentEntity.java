package com.hackathon.ehealthcareproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointment_database")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate appointmentDate;
    private String comments;
    private String remarks;
    private boolean isFulfilled = false;
    @ManyToOne
    @JoinColumn(name = "user_entity_id", referencedColumnName = "id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "doctor_entity_id", referencedColumnName = "id")
    private DoctorEntity doctorEntity;
}
