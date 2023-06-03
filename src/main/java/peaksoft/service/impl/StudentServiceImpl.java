package peaksoft.service.impl;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.entity.Student;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student =new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setGender(studentRequest.getGender());
        student.setIsBlocked(false);
        studentRepository.save(student);
        return new StudentResponse(student.getId(),student.getFirstName(),student.getLastName(),student.getPhoneNumber(),student.getEmail(),student.getStudyFormat());
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepository.getStudentById(id)
                .orElseThrow(() -> new NoSuchElementException("Student with id: " + id + " is not found"));

    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.getAllStudents();
    }
    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student studentResponse = studentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Student with id: " + id + " is not found"));
        studentResponse.setFirstName(studentRequest.getFirstName());
        studentResponse.setLastName(studentRequest.getLastName());
        studentResponse.setPhoneNumber(studentRequest.getPhoneNumber());
        studentResponse.setEmail(studentRequest.getEmail());
        studentResponse.setStudyFormat(studentRequest.getStudyFormat());
        studentResponse.setGender(studentRequest.getGender());
        studentRepository.save(studentResponse);
        return new StudentResponse(studentResponse.getId(),studentResponse.getFirstName(),studentResponse.getLastName(),studentResponse.getPhoneNumber(),studentResponse.getEmail(),studentResponse.getStudyFormat());
    }

    @Override
    public String deleteStudent(Long id) {
        boolean exists=studentRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException("Student with id: " + id + " is not found");
        }
        studentRepository.deleteById(id);
        return "Student with id: " + id + " is deleted...";

    }

    @Override
    public String assignStudentToGroup(Long id, Long groupId) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NullPointerException("Student with id: " + id + " is not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NullPointerException("Group with id: " + groupId + " is not found"));

        if (student == null || group == null) {
            return "Invalid student or group ID";
        }
        if (student.getGroup() != null) {
            return "The student is already assigned to another group.";
        }
        student.setGroup(group);
        studentRepository.save(student);

        return "The student has been successfully assigned to a group.";
    }
    @Override
    public String blockStudent(Long id, Boolean isBlocked) {
        return null;
    }

    @Override
    public List<StudentResponse> getAllBlockStudent(Boolean isBlocked) {
        return null;
    }
}
