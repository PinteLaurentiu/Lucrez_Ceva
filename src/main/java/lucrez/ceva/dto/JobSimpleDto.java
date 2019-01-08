package lucrez.ceva.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.JobAcceptanceType;
import lucrez.ceva.model.enums.JobType;

@Getter
@Setter
@NoArgsConstructor
public class JobSimpleDto {
    private Long id;
    private JobType type;
    private Integer daysSincePosted;
    private String location;
    private String title;
    private boolean bookmarked;
    private JobAcceptanceType acceptanceType;
}
