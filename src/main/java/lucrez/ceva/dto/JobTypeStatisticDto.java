package lucrez.ceva.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.JobType;

@Getter
@Setter
@AllArgsConstructor
public class JobTypeStatisticDto {
    private JobType jobType;
    private Long count;
}
