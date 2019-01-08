package lucrez.ceva.persistence;

import lucrez.ceva.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT ur from user_role ur where ur.user.userLogin.id = :userLoginId")
    List<UserRole> getAllByUserLoginId(@Param("userLoginId") long id);
}
