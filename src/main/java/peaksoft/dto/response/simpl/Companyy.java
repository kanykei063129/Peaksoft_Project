package peaksoft.dto.response.simpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import peaksoft.enums.Country;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Companyy{
    private Long id;
    private String name;
    private Country country;
    private String address;
    private String phoneNumber;
    private List<String> courseName;
    private List<String> groupName;
    private List<String> instructorName;
    private int studentCount;
}
