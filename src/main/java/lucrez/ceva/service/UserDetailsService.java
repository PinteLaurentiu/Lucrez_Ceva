package lucrez.ceva.service;

import lombok.AllArgsConstructor;
import lucrez.ceva.model.UserDetails;
import lucrez.ceva.model.UserLogin;
import lucrez.ceva.model.UserRole;
import lucrez.ceva.model.enums.Role;
import lucrez.ceva.persistence.UserLoginRepository;
import lucrez.ceva.persistence.UserRoleRepository;
import lucrez.ceva.service.staticService.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserLoginRepository userLoginRepo;
    private final UserRoleRepository userRoleRepo;

    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        final UserLogin userLogin = userLoginRepo.findByEmail(email);
        if(userLogin == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        final List<UserRole> userRoles = userRoleRepo.getAllByUserLoginId(userLogin.getId());
        return buildUserDetails(userLogin, userRoles);
    }

    private org.springframework.security.core.userdetails.UserDetails buildUserDetails(UserLogin userLogin,
                                                                                       List<UserRole> userRoles) {
        return UserDetails.builder()
                .id(userLogin.getId())
                .email(userLogin.getEmail())
                .roles(getRoles(userRoles))
                .password(userLogin.getBcrypPassword())
                .isActivated(userLogin.getUser().getActivation().isActivated() ||
                    DateTimeService.addDays(userLogin.getUser().getActivation().getExpiration(), -29).after(new Date()))
                .build();
    }

    private List<Role> getRoles(List<UserRole> userRoles) {
        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
    }
}
