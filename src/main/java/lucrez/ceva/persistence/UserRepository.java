package lucrez.ceva.persistence;

import lucrez.ceva.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameIsContaining(String username);
    List<User> findByNameIsContaining(String username, Pageable pageable);
}
