package nl.fontys.s3.studenthousing.persistence.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.studenthousing.persistence.entity.ResponseEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("STUDENT")
public class StudentEntity extends UserEntity{
    @Column(name = "student_number")
    private String studentNumber;

    @OneToMany(mappedBy = "user")
    private List<ResponseEntity> responses;
}
