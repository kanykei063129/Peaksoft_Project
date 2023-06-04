package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select new peaksoft.dto.response.StudentResponse(s.id, s.firstName,s.lastName, s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s where s.id=?1")
    Optional<StudentResponse> getStudentById(Long id);

//    @Query("select new peaksoft.dto.response.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s")
//    List<StudentResponse> getAllStudents();

//     @Query("select new peaksoft.dto.response.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s where s.isBlocked=?1")//where s.id=:id
    List<StudentResponse>findAllByIsBlocked(Boolean isBlocked);
    @Query("SELECT new peaksoft.dto.response.StudentResponse(s.id, s.firstName,s.lastName, s.phoneNumber,s.email,s.studyFormat,s.isBlocked) FROM Student s WHERE s.studyFormat = 'ONLINE' AND s.group.id = :groupId")
    List<StudentResponse> getAllOnlineStudents(Long groupId);
    @Query("SELECT new peaksoft.dto.response.StudentResponse(s.id, s.firstName,s.lastName, s.phoneNumber,s.email,s.studyFormat,s.isBlocked) FROM Student s WHERE s.studyFormat = 'OFFLINE' AND s.group.id = :groupId")
    List<StudentResponse> getAllOfflineStudents(Long groupId);
}
