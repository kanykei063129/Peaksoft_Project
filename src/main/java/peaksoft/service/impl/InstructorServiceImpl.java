package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructorRepository.save(instructor);
        return new InstructorResponse(instructor.getId(),instructor.getFirstName(),instructor.getLastName(),instructor.getSpecialization());
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        return instructorRepository.getInstructorById(id)
                .orElseThrow(() -> new NoSuchElementException("Instructor with id: " + id + " is not found"));

    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }

    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {
        Instructor instructorResponse = instructorRepository.findById(id).orElseThrow(() -> new NullPointerException("Instructor with id: " + id + " is not found"));
        instructorResponse.setFirstName(instructorRequest.getFirstName());
        instructorResponse.setLastName(instructorRequest.getLastName());
        instructorResponse.setSpecialization(instructorRequest.getSpecialization());
        instructorResponse.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructorRepository.save(instructorResponse);
        return new InstructorResponse(instructorResponse.getId(),instructorResponse.getFirstName(),instructorResponse.getLastName(),instructorResponse.getSpecialization());
    }

    @Override
    public String deleteString(Long id) {
        boolean exists = instructorRepository.existsById(id);
        if(!exists){
            throw new NoSuchElementException("Instructor with id: " + id + " is not found");
        }
        instructorRepository.deleteById(id);
        return "Instructor with id: " + id + " is deleted...";
    }

    @Override
    public String assignInstructorToCompany(Long id, Long companyId) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new NullPointerException("Instructor with id: " + id + " is not found"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NullPointerException("Company with id: " + companyId + " is not found"));
        if(instructor == null || company == null){
            return "Invalid Instructor or Company ID.";
        }
        instructor.setCompanies((List<Company>) company);
        instructorRepository.save(instructor);

        return "The instructor has been successfully assigned to the company.";
    }
    }