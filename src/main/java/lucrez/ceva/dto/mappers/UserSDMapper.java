package lucrez.ceva.dto.mappers;

import lucrez.ceva.dto.UserDetailedDto;
import lucrez.ceva.dto.UserSimpleDto;
import lucrez.ceva.model.User;
import lucrez.ceva.model.UserTag;

import java.util.List;
import java.util.stream.Collectors;

public class UserSDMapper {
    static UserSimpleDto toDto(User user) {
        UserSimpleDto dto = new UserSimpleDto();
        return mapUserSimpleDto(user, dto);
    }

    private static UserSimpleDto mapUserSimpleDto(User user, UserSimpleDto dto) {
        dto.setAvatar(user.getAvatarPath());
        dto.setId(user.getId());
        dto.setLocation(user.getLocation());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        return dto;
    }

    public static List<UserSimpleDto> toDto(List<User> users) {
        return users.stream().map(UserSDMapper::toDto).collect(Collectors.toList());
    }

    public static UserDetailedDto toDetailedDto(User user) {
        UserDetailedDto dto = new UserDetailedDto();
        mapUserSimpleDto(user, dto);
        dto.setBirthday(user.getBirthday());
        dto.setEmail(user.getUserLogin().getEmail());
        dto.setTags(user.getTags().stream().map(UserTag::getTag).collect(Collectors.toList()));
        dto.setGender(user.getGender());
        return dto;
    }
}
