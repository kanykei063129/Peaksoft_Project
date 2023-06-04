package peaksoft.service;

import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    InstructorResponse saveInstructor(InstructorRequest instructorRequest);
    InstructorResponse getInstructorById(Long id);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest);
    String deleteString(Long id);
    String assignInstructorToCompany(Long id,Long companyId);
//    String assignInstructorToCourse(Long id,Long courseId);
}
