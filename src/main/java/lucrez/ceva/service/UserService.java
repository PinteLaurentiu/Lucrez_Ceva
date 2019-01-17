package lucrez.ceva.service;

import lombok.AllArgsConstructor;
import lucrez.ceva.exceptions.ValidationException;
import lucrez.ceva.model.User;
import lucrez.ceva.model.UserDetails;
import lucrez.ceva.model.UserLogin;
import lucrez.ceva.persistence.UserRepository;
import lucrez.ceva.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class UserService implements IUserService {
    private final static Validator<User> validator = new Validator<>(
            UserService::validateEmail,
            UserService::validatePassword
    );

    private UserRepository userRepo;
    private EmailService emailService;

    @Override
    public boolean save(User user) {
        validator.validate(user);
        userRepo.save(user);
        emailService.sendActivationMail(user.getUserLogin().getEmail(), user.getId(), user.getActivation().getUuid());
        return true;
    }

    @Override
    public void activate(long id, String uuid) {
        User user = userRepo.getOne(id);
        if (!user.getActivation().getUuid().equals(uuid))
            throw new ValidationException("UUIDs do no match");
        if (user.getActivation().getExpiration().before(new Date()))
            throw new ValidationException("UUID has expired");
        user.getActivation().setActivated(true);
        userRepo.save(user);
    }

    @Override
    public void update(User user) {
        userRepo.save(user);
    }

    @Override
    public User getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof UserDetails))
            return null;
        UserDetails details = (UserDetails) authentication.getPrincipal();
        Long id = details == null ? null : details.getId();
        return id == null ? null : userRepo.findOne(id);
    }

    @Override
    public User get(Long id) {
        return userRepo.getOne(id);
    }

    @Override
    public String getAvatarPath(User user) {
        return "avatar/"+user.getId();
    }

    @Override
    public void changeAvatarPath(User user, String extension) {
        user.setAvatarPath("/unauthenticated/avatar-" + user.getId() + "/avatar." + extension);
        update(user);
    }

    @Override
    public void delete(Long id) {
        userRepo.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getRange(Integer page, Integer size) {
        return userRepo.findAll(new PageRequest(page,size)).getContent();
    }

    @Override
    public List<User> getAll(String name) {
        return userRepo.findByNameIsContaining(name);
    }

    @Override
    public List<User> getRange(String name, Integer page, Integer size) {
        return userRepo.findByNameIsContaining(name, new PageRequest(page,size));
    }

    @Override
    public void updatePassword(Long id, String bCryptPassword, String password) {
        User user = userRepo.findOne(id);
        if (user == null)
            throw new NullPointerException();
        UserLogin userLogin = user.getUserLogin();
        userLogin.setBcrypPassword(bCryptPassword);
        userLogin.setPassword(password);
        validator.validate(user);
        userRepo.save(user);
    }

    @Override
    public Long size() {
        return userRepo.count();
    }

    private static void validateEmail(User user) {
        if (!EmailService.isEmailValid(user.getUserLogin().getEmail()))
            throw new ValidationException("Email has invalid form");
    }

    private static void validatePassword(User user) {
        if (user.getUserLogin().getPassword() == null)
            throw new NullPointerException();
        validatePasswordLength(user);
        validatePasswordStrength(user);
    }

    private static void validatePasswordStrength(User user) {
        if (!user.getUserLogin().getPassword().matches(".*\\d+.*"))
            throw new ValidationException("Password must contain at least one digit");
        if (!user.getUserLogin().getPassword().matches(".*[a-z]+.*"))
            throw new ValidationException("Password must contain at least one lowercase letter");
        if (!user.getUserLogin().getPassword().matches(".*[A-Z]+.*"))
            throw new ValidationException("Password must contain at least one uppercase letter");
    }

    private static void validatePasswordLength(User user) {
        if (user.getUserLogin().getPassword().length() < 6)
            throw new ValidationException("Password must be at least 6 characters long");
        if (user.getUserLogin().getPassword().length() > 23)
            throw new ValidationException("Password must be at most 23 characters long");
    }
}
