package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.CourseService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;

//    @Override
//    public CourseResponse saveCourse(CourseRequest courseRequest) {
//        Course course = new Course();
//        course.setCourseName(courseRequest.getCourseName());
//        course.setDescription(courseRequest.getDescription());
//        course.setDateOfStart(LocalDate.now());
//        courseRepository.save(course);
//        return new CourseResponse(course.getId(), course.getCourseName(), course.getDateOfStart(), course.getDescription());
//    }
    @Override
    public CourseResponse savaCourse(Long companyId,CourseRequest courseRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NullPointerException("Company with id: " + companyId + "not found"));
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(LocalDate.now());
        course.setDescription(courseRequest.getDescription());
        company.getCourses().add(course);
        courseRepository.save(course);
        return new CourseResponse(course.getId(), course.getCourseName(), course.getDateOfStart(), course.getDescription());
    }
    @Override
    public CourseResponse getCourseById(Long id) {
        return courseRepository.getCourseById(id)
                .orElseThrow(() -> new NoSuchElementException("Course with id: " + id + " is not found"));

    }

    @Override
    public List<CourseResponse> getAllCourse() {
        return courseRepository.getAllCourses();
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        Course courseResponse = courseRepository.findById(id).orElseThrow(() -> new NullPointerException("Course with id: " + id + " is not found"));
        courseResponse.setCourseName(courseRequest.getCourseName());
        courseResponse.setDescription(courseRequest.getDescription());
        courseRepository.save(courseResponse);
        return new CourseResponse(courseResponse.getId(), courseResponse.getCourseName(),courseResponse.getDateOfStart() ,courseResponse.getDescription());
    }

    @Override
    public String deleteString(Long id) {
        boolean exists=courseRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException("Course with id: " + id + " is not found");
        }
        courseRepository.deleteById(id);
        return "Course with id: " + id + " is deleted...";
    }

    @Override
    public String assignCourseToInstructor(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + " is not found"));
        Instructor instructor =instructorRepository.findById(instructorId).orElseThrow(() -> new NullPointerException("Instructor with id: " + instructorId + " is not found"));
        if (course == null || instructor == null){
            return "Invalid course or instructor ID.";
        }
        instructor.setCourses((List<Course>) course);
        instructorRepository.save(instructor);
        return "The course has been successfully assigned to the instructor";
    }
}