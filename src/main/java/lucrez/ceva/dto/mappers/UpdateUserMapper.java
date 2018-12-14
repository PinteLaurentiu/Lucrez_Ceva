package lucrez.ceva.dto.mappers;

import lucrez.ceva.dto.UpdateUserDto;
import lucrez.ceva.model.AbilityTag;
import lucrez.ceva.model.User;
import lucrez.ceva.service.staticService.DateTimeService;

import java.util.HashSet;
import java.util.Set;

public class UpdateUserMapper {
    public static void updateUserDetails(User user, UpdateUserDto userDto) {
        user.setGender(userDto.getGender());
        user.setBirthday(DateTimeService.dateFromString(userDto.getBirthday()));
        user.setAbilities(userDto.getAbilities());
        user.setLocation(userDto.getLocation());
        user.setPhone(userDto.getPhone());
        user.setTags(createAbilityTags(user, userDto));
    }

    private static Set<AbilityTag> createAbilityTags(User user, UpdateUserDto userDto) {
        Set<AbilityTag> abilityTags = new HashSet<>();
        for (String abilityString : userDto.getTags()) {
            AbilityTag tag = new AbilityTag();
            tag.setAbility(abilityString);
            tag.setUser(user);
            abilityTags.add(tag);
        }
        return abilityTags;
    }
}
