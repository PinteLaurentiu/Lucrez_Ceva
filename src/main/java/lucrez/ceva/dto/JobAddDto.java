package lucrez.ceva.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.JobType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JobAddDto {
    private String description;
    private String title;
    private String location;
    private Integer days;
    private JobType jobType;
    private Double salary;
    private List<String> tags;
}
