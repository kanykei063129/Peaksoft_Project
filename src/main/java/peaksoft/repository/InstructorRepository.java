package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.entity.Instructor;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    @Query("select new peaksoft.dto.response.InstructorResponse(i.id,i.firstName,i.lastName,i.specialization) from Instructor i where i.id=?1")
    Optional<InstructorResponse> getInstructorById(Long id);
    @Query("SELECT new peaksoft.dto.response.InstructorResponse(i.id,i.firstName,i.lastName,i.specialization) FROM Instructor i")
    List<InstructorResponse> getAllInstructors();

    @Query("select count(s) from Instructor i join i.courses c join  c.groups g join g.students s where i.id=:instructorId")
    int getAllStudentSize(Long instructorId);
    @Query("select g.groupName from Instructor i join i.courses c join  c.groups g where i.id =:instructorId")
    List<String> getAllGroupName(Long instructorId);

}
