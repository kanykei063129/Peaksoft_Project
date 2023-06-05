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

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setGender(studentRequest.getGender());
        student.setIsBlocked(false);
        studentRepository.save(student);
        return new StudentResponse(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getPhoneNumber(), student.getStudyFormat(), student.getIsBlocked());
    }


    @Override
    public List<StudentResponse> getAllStudents(Long groupId) {
        return studentRepository.getAllStudents(groupId);
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        return studentRepository.getStudentById(studentId).orElseThrow(() -> new NoSuchElementException("Student with id:" + studentId + " is not found"));
    }
    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student with id: " + id + " is not found"));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setGender(studentRequest.getGender());
        student.setIsBlocked(false);
        studentRepository.save(student);
        return StudentResponse.builder().id(student.getId()).firstName(studentRequest.getFirstName()).lastName(studentRequest.getLastName()).phoneNumber(studentRequest.getPhoneNumber()).email(studentRequest.getEmail()).studyFormat(studentRequest.getStudyFormat()).build();
    }

    @Override
    public String deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
        return "Student deleted successfully!";
    }

    @Override
    public String assignStudentsToGroup(Long studentId, Long groupId) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student with id: " + studentId + " is not found"));
            Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group with id: " + groupId + " is not found"));
            group.getStudents().add(student);
            student.setGroup(group);
            studentRepository.save(student);
            groupRepository.save(group);
            return "The student has been successfully assigned to a group.";
        } catch (RuntimeException e) {
            return "Failed to delete student: " + e.getMessage();
        }
    }


    @Override
    public List<StudentResponse> getAllIsBlocked(Boolean isBlocked) {
        return studentRepository.findByIsBlocked(isBlocked);
    }


    @Override
    public List<StudentResponse> filterStudents(StudyFormat studyFormat) {
        return studentRepository.findByStudyFormat(studyFormat);

    }

    @Override
    public String blockUnblockStudent(Long studentId, Boolean block) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student with id:" + studentId + "is not found"));
        student.setIsBlocked(block);
        studentRepository.save(student);
        return "Blocked, Student with id:" + studentId + "is blocked";
    }
}