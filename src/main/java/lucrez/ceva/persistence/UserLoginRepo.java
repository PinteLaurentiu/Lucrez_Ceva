package lucrez.ceva.persistence;

import lucrez.ceva.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepo extends JpaRepository<UserLogin, Long> {
    UserLogin findByEmail(String email);
}
