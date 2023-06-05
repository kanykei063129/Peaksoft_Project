package peaksoft.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;
@Getter
@Setter
@AllArgsConstructor
public class StudentRequest {
    @NotEmpty(message = "FirstName should not be empty")
    private String firstName;
    @NotEmpty(message = "LastName should not be empty")
    private String lastName;
    @Pattern(regexp = "^\\+996\\d{13}$", message = "Phone number must start with +996 and contain 13 digits")
    private String phoneNumber;
    @NotEmpty(message = "Email should not be empty")
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
