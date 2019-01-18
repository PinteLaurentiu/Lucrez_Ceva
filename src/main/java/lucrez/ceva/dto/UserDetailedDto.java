package lucrez.ceva.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.Gender;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailedDto extends UserSimpleDto {
    private String email;
    private List<String> tags;
    private Date birthday;
    private Gender gender;
}
