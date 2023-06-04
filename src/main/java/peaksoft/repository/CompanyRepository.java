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

    @Query("select new peaksoft.dto.response.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c")
    List<CompanyResponse> getAllCompanies();
}
