package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(generator = "instructor_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "instructor_gen",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String  lastName;
    @Column(unique = true)
    private String phoneNumber;
    private  String specialization;

    @ManyToMany(mappedBy = "instructors",cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Company>companies;

    @OneToMany(mappedBy = "instructor",cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Course> courses;
}
