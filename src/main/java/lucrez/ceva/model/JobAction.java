package lucrez.ceva.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.Action;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobAction {
    Job job;
    Date date;
    Action action;
}
