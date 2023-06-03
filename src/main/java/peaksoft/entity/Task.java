package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_gen")
    @SequenceGenerator(name = "task_gen",sequenceName = "task_seq",allocationSize = 1)
    private Long id;
    @Value("task_name")
    private String taskName;
    @Value("task_text")
    private String taskText;
    private LocalDate deadline;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH,CascadeType.REFRESH})
    private Lesson  lesson;
}
