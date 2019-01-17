package lucrez.ceva.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAdminDto {
    private String name;
    private Long id;
    private String phone;
    private String avatar;
    private String location;
}
