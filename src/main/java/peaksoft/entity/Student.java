package peaksoft.entity;


import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;

import static jakarta.persistence.CascadeType.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(generator = "student_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean isBlocked;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Group group;
}
