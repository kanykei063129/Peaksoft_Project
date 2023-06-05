package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simpl.Instructorr;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        try {
            Instructor instructor = new Instructor();
            instructor.setFirstName(instructorRequest.getFirstName());
            instructor.setLastName(instructorRequest.getLastName());
            instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
            instructor.setSpecialization(instructorRequest.getSpecialization());
            instructorRepository.save(instructor);
            return InstructorResponse.builder().id(instructor.getId()).firstName((instructorRequest.getFirstName())).lastName(instructorRequest.getLastName()).phoneNumber(instructorRequest.getPhoneNumber()).specialization(instructorRequest.getSpecialization()).build();
        } catch (Exception e) {
            throw new NoSuchElementException("Student with id:" + instructorRequest + " is not found");
        }
    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }

    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        return instructorRepository.getInstructorById(instructorId).orElseThrow(() -> new RuntimeException("Instructor with id: " + instructorId + " not found!"));
    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new RuntimeException("Instructor with id: " + instructorId + "not found!"));
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return InstructorResponse.builder().id(instructor.getId()).firstName(instructorRequest.getFirstName()).lastName(instructorRequest.getLastName()).phoneNumber(instructorRequest.getPhoneNumber()).specialization(instructorRequest.getSpecialization()).build();
    }

    @Override
    public String deleteInstructor(Long instructorId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId)
                    .orElseThrow(() -> new NoSuchElementException("Instructor with id: " + instructorId + " is not found"));
            List<Company> companies = instructor.getCompanies();
            for (Company c : companies) {
                c.getInstructors().remove(instructor);
            }
            instructor.getCompanies().clear();
            instructorRepository.delete(instructor);
            return "Instructor with id: " + instructorId + " is deleted...";

        } catch (RuntimeException e) {
            return "Instructor with id: " + instructorId + " is not found" + e.getMessage();
        }
    }

    @Override
    public String assignInstructorToCompany(Long instructorId, Long companyId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id:" + instructorId + "is not found"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id:" + companyId + "is not found"));
        company.getInstructors().add(instructor);
        instructor.getCompanies().add(company);
        instructorRepository.save(instructor);
        companyRepository.save(company);
        return "The instructor has been successfully assigned to the company.";
    }

    @Override
    public Instructorr infoInstructor(Long instructorId) {
        InstructorResponse i = instructorRepository.getInstructorById(instructorId).orElseThrow(() -> new NoSuchElementException("Company with id:" + instructorId + "is not found"));
        return Instructorr.builder().id(i.getId()).firstName(i.getFirstName()).lastName(i.getLastName()).specialization(i.getSpecialization()).phoneNumber(i.getPhoneNumber()).groupName(instructorRepository.getAllGroupName(instructorId)).studentCount(instructorRepository.getAllStudentSize(instructorId)).build();
    }

    @Override
    public String assignInstructorToCourse(Long instructorId, Long courseId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id:" + instructorId + " is not found"));
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course with id:" + courseId + " is not found"));
            course.setInstructor(instructor);
            instructor.getCourses().add(course);
            courseRepository.save(course);
            return "Course with id successfully  assign!";
        } catch (RuntimeException e) {
            return "Failed to assign  instructor: " + e.getMessage();
        }
    }
}