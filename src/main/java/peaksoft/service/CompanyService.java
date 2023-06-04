package peaksoft.service;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Company;

import java.util.List;

public interface CompanyService {
    CompanyResponse saveCompany(CompanyRequest companyRequest);
    CompanyResponse getCompanyById(Long id);
    List<CompanyResponse> getAllCompanies();
    CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);
    String deleteString(Long id);
}
