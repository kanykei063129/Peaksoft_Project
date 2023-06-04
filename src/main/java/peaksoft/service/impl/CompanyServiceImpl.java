package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.enums.Country;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CompanyService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company=new Company();
       company.setName(companyRequest.getName());
       company.setCountry(companyRequest.getCountry());
       company.setAddress(companyRequest.getAddress());
       company.setPhoneNumber(companyRequest.getPhoneNumber());
       companyRepository.save(company);
        return new CompanyResponse(company.getId(),company.getName(),company.getCountry(),company.getAddress(),company.getPhoneNumber());
    }
    @Override
    public CompanyResponse getCompanyById(Long id) {
        return companyRepository.getCompanyById(id)
                .orElseThrow(() -> new NoSuchElementException("Company with id: " + id + " is not found"));

    }
    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        Company companyResponse= companyRepository.findById(id).orElseThrow(() -> new NullPointerException("Company with id: " + id + " is not found"));
        companyResponse.setName(companyRequest.getName());
        companyResponse.setCountry(companyRequest.getCountry());
        companyResponse.setAddress(companyRequest.getAddress());
        companyResponse.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(companyResponse);
        return new CompanyResponse(companyResponse.getId(),companyResponse.getName(),companyResponse.getCountry(),companyResponse.getAddress(),companyResponse.getPhoneNumber());
    }

    @Override
    public String deleteString(Long id) {
        boolean exists=companyRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException("Company with id: " + id + " is not found");
        }
        companyRepository.deleteById(id);
        return "Company with id: " + id + " is deleted...";
    }
}