package lucrez.ceva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobPageableFilter extends JobFilter {
    Integer page;
    Integer pageSize;
}
