package lucrez.ceva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDetailsWrapper implements UserDetails, CredentialsContainer {
    @NonNull
    private UserLogin userLogin;
    @NonNull
    private List<UserRole> userRoles;

    @JsonIgnore
    private String password;

    @Override
    public void eraseCredentials() {
        password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> roles = new HashSet<>();
        for(UserRole userRole : userRoles){
            roles.add(userRole.getRole().toAuthority());
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return userLogin.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
