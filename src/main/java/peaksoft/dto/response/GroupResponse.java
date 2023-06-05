package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Setter
@Getter
public class GroupResponse {
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;

    public GroupResponse(Long id, String groupName, String imageLink, String description) {
        this.id = id;
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
    public GroupResponse() {
    }
}
