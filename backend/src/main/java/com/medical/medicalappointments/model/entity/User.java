package com.medical.medicalappointments.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.medical.medicalappointments.model.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column()
    private String avatarFileName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_info_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PatientInfo patientInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_info_id", referencedColumnName = "id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DoctorInfo doctorInfo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
