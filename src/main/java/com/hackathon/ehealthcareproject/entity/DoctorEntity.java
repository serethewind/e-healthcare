package com.hackathon.ehealthcareproject.entity;

import com.hackathon.ehealthcareproject.entity.AppointmentEntity;
import com.hackathon.ehealthcareproject.entity.DepartmentEntity;
import com.hackathon.ehealthcareproject.entity.PrescriptionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doctor_database")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String specialization;
    private String phoneNumber;
    private String email;
    private boolean isAvailable = true;
    @Enumerated(EnumType.STRING)
    private Set<DaysOfWeek> availableDays = new HashSet<>();
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate dateUpdated;
    @ManyToMany
    @JoinTable(
            name = "doctor_department_table",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id")
    )
    private List<DepartmentEntity> department = new ArrayList<>();
    @OneToMany(mappedBy = "doctorEntity", cascade = CascadeType.ALL)
    private List<PrescriptionEntity> prescriptionEntities;
    @OneToMany(mappedBy = "doctorEntity", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointmentEntities;
}
