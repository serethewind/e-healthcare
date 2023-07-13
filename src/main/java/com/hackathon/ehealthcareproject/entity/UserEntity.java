package com.hackathon.ehealthcareproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Column(nullable = false)
    private String firstName;

    private String otherName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    private String gender;
    @Column(nullable = false)
    private String password;
    private String imageUri;
    private String phoneNumber;
    private String address;
    private String nextOfKin;
    private LocalDate dateOfBirth;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart shoppingCart;
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
