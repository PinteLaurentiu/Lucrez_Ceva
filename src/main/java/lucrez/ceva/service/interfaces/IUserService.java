package lucrez.ceva.service.interfaces;

import lucrez.ceva.model.User;

public interface IUserService {
    boolean save(User user);

    void activate(long id, String uuid);

    void update(User user);

    User getCurrent();

    User get(Long id);

    String getAvatarPath(User user);

    void changeAvatarPath(User user);

    void delete(Long id);
}
