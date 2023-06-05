package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simpl.Companyy;
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
    public String saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company);
        return "Saved Company";
    }


    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }


    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return companyRepository.getCompanyById(companyId) .orElseThrow(() -> new NoSuchElementException("Company with id:" + companyId + "is not found"));
    }



    @Override
    public String updateCompany(Long companyId, CompanyRequest companyRequest) {
        Company company1 = companyRepository.findById(companyId).orElseThrow(() ->
                new RuntimeException("Company with id : " + companyId + " not found"));
        company1.setName(companyRequest.getName());
        company1.setCountry(companyRequest.getCountry());
        company1.setAddress(companyRequest.getAddress());
        company1.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company1);
        return "Company successfully updated ...!";
    }


    @Override
    public String deleteCompanyById(Long companyId) {
        companyRepository.deleteById(companyId);
        return "Company deleted successfully!";
    }


    @Override
    public Companyy infoCompany(Long companyId) {
        CompanyResponse c = companyRepository.getCompanyById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id:" + companyId + "is not found"));
        return Companyy.builder()
                .id(c.getId())
                .name(c.getName())
                .address(c.getAddress())
                .country(c.getCountry())
                .phoneNumber(c.getPhoneNumber())
                .courseName(companyRepository.getAllCourseName(companyId))
                .groupName(companyRepository.getAllGroupName(companyId))
                .studentCount(companyRepository.getAllStudentSize(companyId))
                .instructorName(companyRepository.getAllInstructorName(companyId))
                .build();
    }
    }