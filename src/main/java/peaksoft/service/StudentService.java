package peaksoft.service;

import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest studentRequest);
    StudentResponse getStudentById(Long id);
    List<StudentResponse> getAllStudents(Long groupId, StudyFormat studyFormat);
    StudentResponse updateStudent(Long id, StudentRequest studentRequest);
    String deleteStudent(Long id);
    String assignStudentToGroup(Long id,Long groupId);
//    List<StudentResponse> getStudentsByIsBlockedOrNot(Boolean isBlocked);
    String blockUnblockStudent(Long studentId, Boolean block);










//    List<StudentResponse> getAllBlockStudent(Boolean isBlocked);
}
