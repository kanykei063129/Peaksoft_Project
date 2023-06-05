package peaksoft.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class InstructorRequest {
    @NotEmpty(message = "FirstName should not be empty")
    private String firstName;
    @NotEmpty(message = "LastName should not be empty")
    private String lastName;
    @Pattern(regexp = "^\\+996\\d{13}$", message = "Phone number must start with +996 and contain 13 digits")
    private String phoneNumber;
    @NotNull(message = "Specialization should not be empty")
    private String specialization;
}
