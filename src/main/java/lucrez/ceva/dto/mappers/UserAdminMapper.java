package lucrez.ceva.dto.mappers;

import lucrez.ceva.dto.UserAdminDto;
import lucrez.ceva.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserAdminMapper {
    public static UserAdminDto toDto(User user) {
        UserAdminDto dto = new UserAdminDto();
        dto.setAvatar(user.getAvatarPath());
        dto.setId(user.getId());
        dto.setLocation(user.getLocation());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        return dto;
    }

    public static List<UserAdminDto> toDto(List<User> users) {
        return users.stream().map(UserAdminMapper::toDto).collect(Collectors.toList());
    }
}
