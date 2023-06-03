package peaksoft.service;

import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest studentRequest);
    StudentResponse getStudentById(Long id);
    List<StudentResponse> getAllStudents();
    StudentResponse updateStudent(Long id, StudentRequest studentRequest);
    String deleteStudent(Long id);
    String assignStudentToGroup(Long id,Long groupId);
    String blockStudent(Long id,Boolean isBlocked);
    List<StudentResponse> getAllBlockStudent(Boolean isBlocked);
}
