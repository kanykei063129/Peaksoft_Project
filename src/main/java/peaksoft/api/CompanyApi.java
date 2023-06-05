package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simpl.Companyy;
import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    public String saveCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }
    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public String updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(id,companyRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompanyById(id);
    }
    @GetMapping("/info/{companyId}")
    public Companyy company(@PathVariable Long companyId){
        return companyService.infoCompany(companyId);
    }
}