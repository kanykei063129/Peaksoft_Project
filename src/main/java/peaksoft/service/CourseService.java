package peaksoft.service;

import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
    String saveCourse(Long companyId, CourseRequest courseRequest);
    List<CourseResponse> getAllCourse(Long companyId, String ascOrDesc);
    String getCourseById(Long courseId);
    CourseResponse updateCourse(Long courseId, CourseRequest courseRequest);
    String deleteCourseById(Long courseId);
}
