package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
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
    private final CompanyRepository companyRepository;

    @Override
    public String saveCourse(Long companyId, CourseRequest courseRequest) {
        try {
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new NoSuchElementException("Company with id: " + companyId + " not found"));
            Course course = new Course();
            course.setCourseName(courseRequest.getCourseName());
            course.setDateOfStart(LocalDate.now());
            course.setDescription(courseRequest.getDescription());
            company.getCourses().add(course);
            course.setCompany(company);
            courseRepository.save(course);

            return "Course saved successfully!";
        } catch (Exception e) {
            return "Failed to save course: " + e.getMessage();
        }
    }

    @Override
    public List<CourseResponse> getAllCourse(Long companyId, String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            return courseRepository.getAllSortedCourseA(companyId);
        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
            return courseRepository.getAllSortedCourseD(companyId);
        } else {
            throw new NullPointerException("The list is empty");
        }
    }

    @Override
    public String getCourseById(Long courseId) {
        try {
            courseRepository.getCourseById(courseId)
                    .orElseThrow(() -> new NullPointerException("Course with id: " + courseId + " is not found"));

            return "Course " + courseId + " successfully!";
        } catch (Exception e) {
            return "Failed to get course: " + e.getMessage();
        }
    }

    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course courseResponse = courseRepository.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + " is not found"));
        courseResponse.setCourseName(courseRequest.getCourseName());
        courseResponse.setDescription(courseRequest.getDescription());
        courseRepository.save(courseResponse);
        return new CourseResponse(courseResponse.getId(), courseResponse.getCourseName(), courseResponse.getDescription());
    }

    @Override
    public String deleteCourseById(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if (!exists) {
            throw new NoSuchElementException("Course with id: " + courseId + " is not found");
        }
        courseRepository.deleteById(courseId);
        return "Course with id: " + courseId + " is deleted...";
        }
    }