package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.intellij.lang.annotations.Pattern;
import org.springframework.beans.factory.annotation.Value;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;

import java.util.List;

@Entity
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_gen")
    @SequenceGenerator(name = "student_gen",sequenceName = "student_seq",allocationSize = 1)
    private Long id;
    @Value("first_name")
    private String firstName;
    @Value("last_name")
    private String lastName;
    @Value("phone_number")
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Value("study_format")
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean isBlocked;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH,CascadeType.REFRESH})
    private Group group;
}
