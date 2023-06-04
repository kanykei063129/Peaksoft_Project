package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_gen")
    @SequenceGenerator(name = "course_gen",sequenceName = "course_seq",allocationSize = 1)
    private Long id;
    @Value("course_name")
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Company company;
    @ManyToMany(mappedBy = "courses",cascade = {CascadeType.ALL})
    private List<Group>groups;
    @OneToMany(mappedBy = "course",cascade = {CascadeType.MERGE, CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Lesson>lessons;

}
