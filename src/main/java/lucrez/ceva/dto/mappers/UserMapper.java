package lucrez.ceva.dto.mappers;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.UserDto;
import lucrez.ceva.exceptions.ValidationException;
import lucrez.ceva.model.*;
import lucrez.ceva.model.enums.Role;
import lucrez.ceva.service.Validator;
import lucrez.ceva.service.staticService.AvatarService;
import lucrez.ceva.service.staticService.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class UserMapper {
    private PasswordEncoder userPasswordEncoder;

    private static Validator<UserDto> validator = new Validator<>(
            UserMapper::validatePassword
    );

    public User dtoToModelUser(UserDto dto) {
        validator.validate(dto);
        User user = new User();
        mapSimpleProperties(dto, user);
        user.setUserRoles(createUserRoles(user));
        user.setActivation(createActivation(user));
        user.setUserLogin(createUserLogin(user, dto));
        return user;
    }

    private void mapSimpleProperties(UserDto dto, User user) {
        user.setAvatarPath(AvatarService.getDefaultAvatar());
        user.setName(dto.getName());
        user.setTags(new HashSet<>());
    }

    private Set<UserRole> createUserRoles(User user) {
        Set<UserRole> roleSet = new HashSet<>();
        UserRole role = new UserRole();
        role.setRole(Role.User);
        role.setUser(user);
        roleSet.add(role);
        return roleSet;
    }

    private UserActivation createActivation(User user) {
        UserActivation activation = new UserActivation();
        activation.setActivated(false);
        activation.setExpiration(DateTimeService.addDays(new Date(), 30));
        activation.setUuid(UUID.randomUUID().toString());
        activation.setUser(user);
        return activation;
    }

    private UserLogin createUserLogin(User user, UserDto dto) {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(dto.getEmail());
        userLogin.setPassword(dto.getPassword());
        userLogin.setBcrypPassword(encodePassword(dto.getPassword()));
        userLogin.setUser(user);
        return userLogin;
    }

    public String encodePassword(String password) {
        return userPasswordEncoder.encode(password);
    }

    private static void validatePassword(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getPasswordAgain()))
            throw new ValidationException("passwords do not match");
    }
}
