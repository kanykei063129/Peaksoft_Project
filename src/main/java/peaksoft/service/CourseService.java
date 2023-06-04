package peaksoft.service;

import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
    CourseResponse savaCourse(Long companyId, CourseRequest courseRequest);

    CourseResponse getCourseById(Long id);

    List<CourseResponse> getAllCourse(Long companyId, String ascOrDesc);

    CourseResponse updateCourse(Long id, CourseRequest courseRequest);

    String deleteString(Long id);
}






















//    String assignCourseToInstructor(Long courseId,Long instructorId);
