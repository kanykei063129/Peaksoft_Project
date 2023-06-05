package peaksoft.service;

import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.simpl.Instructorr;
import peaksoft.entity.Company;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    InstructorResponse saveInstructor(InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(Long instructorId);
    InstructorResponse updateInstructor(Long instructorId,InstructorRequest instructorRequest);
    String deleteInstructor(Long instructorId);
    String assignInstructorToCompany(Long instructorId,Long companyId);
    Instructorr infoInstructor(Long instructorId);
    String assignInstructorToCourse(Long instructorId, Long courseId);
}


