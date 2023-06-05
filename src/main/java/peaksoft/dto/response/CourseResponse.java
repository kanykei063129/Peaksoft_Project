package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Company;

import java.time.LocalDate;
@Builder
@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String courseName;
//    private LocalDate dateOfStart;
    private String description;

    public CourseResponse() {
    }

    public CourseResponse(Long id, String courseName,String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;


    }
}
