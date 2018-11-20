package lucrez.ceva.model.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    User,
    Admin;

    private static final String RolePrefix = "ROLE_";

    public GrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(RolePrefix + toString().toUpperCase());
    }
}
