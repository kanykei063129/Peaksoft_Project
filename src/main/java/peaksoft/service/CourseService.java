package peaksoft.service;

import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
//    CourseResponse saveCourse(CourseRequest courseRequest);
    CourseResponse savaCourse(Long companyId,CourseRequest courseRequest);
    CourseResponse getCourseById(Long id);
    List<CourseResponse> getAllCourse();
    CourseResponse updateCourse(Long id, CourseRequest courseRequest);
    String deleteString(Long id);
    String assignCourseToInstructor(Long courseId,Long instructorId);
}
