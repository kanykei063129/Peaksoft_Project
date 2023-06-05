package peaksoft.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import peaksoft.enums.Country;

@Setter
@Getter
@AllArgsConstructor
public class CompanyRequest {
    @Column(unique = true)
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Country should not be empty")
    @Enumerated(EnumType.STRING)
    private Country country;
    @NotEmpty(message = "Address should not be empty")
    private String address;
    @Column(unique = true)
    @Pattern(regexp = "\\+996\\d{13}",message = "Phone number must start with '+996' and contain 13 digits")
    private String phoneNumber;
}
