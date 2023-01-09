package nl.fontys.s3.studenthousing.persistence.entity.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@Getter
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_role", discriminatorType = DiscriminatorType.STRING)
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "prefix")
    private String prefix;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "user_role", insertable = false, updatable = false)
    private String userRole;
}
