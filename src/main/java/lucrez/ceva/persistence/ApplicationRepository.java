package lucrez.ceva.persistence;

import lucrez.ceva.model.Application;
import lucrez.ceva.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> getByUser(User user);
}
