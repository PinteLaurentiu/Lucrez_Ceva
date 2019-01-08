package lucrez.ceva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.JobAcceptanceType;
import lucrez.ceva.model.enums.JobType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JobFilter {
    private List<String> locations;
    private List<JobType> types;
    private String word;
    private JobAcceptanceType jobAcceptanceType;
}
