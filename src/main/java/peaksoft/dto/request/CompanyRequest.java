package peaksoft.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Country;
@Setter
@Getter
@AllArgsConstructor
public class CompanyRequest {
    private String name;
    private Country country;
    private String address;
    private String phoneNumber;
}
