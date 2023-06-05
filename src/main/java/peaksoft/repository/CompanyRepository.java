package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.entity.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
     @Query("select new peaksoft.dto.response.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c where c.id=?1")//where s.id=:id
    Optional<CompanyResponse> getCompanyById(Long id);

    @Query("select new peaksoft.dto.response.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber)from Company c")
    List<CompanyResponse> getAllCompanies();

    @Query("select count (s)from Company c join Instructor i join i.courses co join co.groups g join Student s where c.id=:companyId")
    int getAllStudentSize(Long companyId);


    @Query("select i.firstName from Company com join Instructor i  where com.id=:companyId")

    List<String> getAllInstructorName(Long companyId);
    @Query("select g.groupName from Company com  join Course c join Group g where com.id=:companyId")
    List<String> getAllGroupName(Long companyId);
    @Query("select c.courseName from Company com  join Course c  where com.id=:companyId")
    List<String> getAllCourseName(Long companyId);
}
