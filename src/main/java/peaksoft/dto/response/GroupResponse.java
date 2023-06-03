package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupResponse {
    private Long id;
    private String groupName;
    private String description;
    private String imageLink;

    public GroupResponse(Long id, String groupName, String description, String imageLink) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
        this.imageLink = imageLink;
    }

    public GroupResponse() {
    }
}
