package peaksoft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Company;

@Getter
@Setter
@AllArgsConstructor
public class CourseRequest {
    private String courseName;
    private String description;

}
