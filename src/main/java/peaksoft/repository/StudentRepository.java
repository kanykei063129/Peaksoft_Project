package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select new peaksoft.dto.response.StudentResponse(s.id, s.firstName,s.lastName, s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s where s.id=?1")
    Optional<StudentResponse> getStudentById(Long id);

    @Query("select new peaksoft.dto.response.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s where s.group.id=:groupId")
    List<StudentResponse> getAllStudents(Long groupId);
    List<StudentResponse>findByStudyFormat(StudyFormat studyFormat);
    List<StudentResponse> findByIsBlocked(Boolean isBlocked);
}
