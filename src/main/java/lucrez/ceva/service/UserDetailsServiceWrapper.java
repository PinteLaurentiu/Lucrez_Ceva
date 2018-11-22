package lucrez.ceva.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lucrez.ceva.model.UserDetailsWrapper;
import lucrez.ceva.model.UserLogin;
import lucrez.ceva.model.UserRole;
import lucrez.ceva.persistence.UserLoginRepo;
import lucrez.ceva.persistence.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsServiceWrapper")
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class UserDetailsServiceWrapper implements UserDetailsService {

    private final @NonNull UserLoginRepo userLoginRepo;
    private final @NonNull UserRoleRepo userRoleRepo;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserLogin userLogin = userLoginRepo.findByEmail(email);

        if(userLogin == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        final List<UserRole> userRoles = userRoleRepo.getAllByUserLoginId(userLogin.getId());
        final UserDetailsWrapper userDetailsWrapper = new UserDetailsWrapper(userLogin, userRoles);
        userDetailsWrapper.setPassword(userLogin.getBcrypPassword());

        return userDetailsWrapper;
    }
}
