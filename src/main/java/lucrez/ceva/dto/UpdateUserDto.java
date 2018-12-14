package lucrez.ceva.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.Gender;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDto {
    private String birthday;
    private String location;
    private Gender gender;
    private String phone;
    private String abilities;
    private List<String> tags;
}
