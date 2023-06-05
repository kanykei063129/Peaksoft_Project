package peaksoft.service;

import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest studentRequest);
    List<StudentResponse> getAllStudents(Long groupId);
    StudentResponse getStudentById(Long studentId);
    StudentResponse updateStudent(Long studentId, StudentRequest studentRequest);
    String deleteStudent(Long studentId);
    String assignStudentsToGroup(Long studentId,Long groupId);

    List<StudentResponse>getAllIsBlocked(Boolean isBlocked);


    List<StudentResponse>filterStudents(StudyFormat studyFormat);

    String blockUnblockStudent(Long studentId, Boolean block);
}