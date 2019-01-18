package lucrez.ceva.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JobDetailedDto extends JobSimpleDto {
    private String description;
    private List<String> tags;
    private String avatarPath;
    private String username;
    private Double salary;
    private List<UserAdminDto> applications;
    private Long userId;
}
