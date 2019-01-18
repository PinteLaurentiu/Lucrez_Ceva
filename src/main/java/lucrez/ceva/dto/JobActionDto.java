package lucrez.ceva.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.Action;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class JobActionDto {
    private JobSimpleDto job;
    private Date actionDate;
    private Action action;
}
