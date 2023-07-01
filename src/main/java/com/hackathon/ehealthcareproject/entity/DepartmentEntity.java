package com.hackathon.ehealthcareproject.entity;

import com.hackathon.ehealthcareproject.entity.users.StaffEntity;
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
@Table(name = "hospital_database")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private HospitalEntity hospital;
    @ManyToMany(mappedBy = "department")
    private List<DoctorEntity> doctorEntityList;
    @ManyToMany(mappedBy = "department")
    private List<StaffEntity> staffEntityList;
}
