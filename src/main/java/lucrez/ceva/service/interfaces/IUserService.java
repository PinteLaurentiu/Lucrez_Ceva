package lucrez.ceva.service.interfaces;

import lucrez.ceva.model.User;
import lucrez.ceva.model.UserLogin;

import java.util.List;

public interface IUserService {
    boolean save(User user);

    void activate(long id, String uuid);

    void update(User user);

    User getCurrent();

    User get(Long id);
}
