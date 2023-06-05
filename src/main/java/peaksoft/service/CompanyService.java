package peaksoft.service;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simpl.Companyy;
import peaksoft.entity.Company;

import java.util.List;

public interface CompanyService {
    String saveCompany(CompanyRequest companyRequest);
    List<CompanyResponse>getAllCompanies();
    CompanyResponse getCompanyById(Long companyId);

    String updateCompany(Long companyId, CompanyRequest companyRequest);

    String deleteCompanyById(Long companyId);

    Companyy infoCompany(Long companyId);
}

