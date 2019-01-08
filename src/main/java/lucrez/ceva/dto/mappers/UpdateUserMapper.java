package lucrez.ceva.dto.mappers;

import lucrez.ceva.dto.UpdateUserDto;
import lucrez.ceva.model.JobTag;
import lucrez.ceva.model.User;
import lucrez.ceva.model.UserTag;
import lucrez.ceva.service.staticService.DateTimeService;

import java.util.stream.Collectors;

public class UpdateUserMapper {
    public static void updateUserDetails(User user, UpdateUserDto userDto) {
        user.setGender(userDto.getGender());
        user.setBirthday(DateTimeService.dateFromString(userDto.getBirthday()));
        user.setLocation(userDto.getLocation());
        user.setPhone(userDto.getPhone());
        user.getTags().clear();
        user.getTags().addAll(userDto.getTags().stream().map(x->createTag(x, user)).collect(Collectors.toSet()));
    }

    private static UserTag createTag(String tag, User user) {
        UserTag userTag = new UserTag();
        userTag.setTag(tag);
        userTag.setUser(user);
        return userTag;
    }

}
