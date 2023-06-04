package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
     @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description) from Course  c where c.id=?1")//where s.id=:id
    Optional<CourseResponse> getCourseById(Long id);
    @Query("select new peaksoft.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart asc ")
    List<CourseResponse> getAllSortedCourseA(Long companyId);

    @Query("select new peaksoft.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart desc ")
    List<CourseResponse> getAllSortedCourseD(Long companyId);
}
