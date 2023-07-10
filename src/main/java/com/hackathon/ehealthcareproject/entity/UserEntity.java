package com.hackathon.ehealthcareproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_database")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String otherName;
    private String lastName;
    private String username;
    private String email;
    private String gender;
    private String password;
    private String imageUri;
    private String phoneNumber;
    private String address;
    private String nextOfKin;
    private LocalDate dateOfBirth;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;
    private boolean isAvailable = true;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RolesEntity> roles = new HashSet<>();
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate dateUpdated;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<PrescriptionEntity> prescriptionEntities;
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointmentEntities;
}
