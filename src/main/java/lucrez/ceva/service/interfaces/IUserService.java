package lucrez.ceva.service.interfaces;

import lucrez.ceva.model.User;

import java.util.List;

public interface IUserService {
    boolean save(User user);

    void activate(long id, String uuid);

    void update(User user);

    User getCurrent();

    User get(Long id);

    void changeAvatarPath(User user, String extension);

    String getAvatarPath(User user);

    void delete(Long id);

    List<User> getAll();

    List<User> getRange(Integer page, Integer size);

    List<User> getAll(String name);

    List<User> getRange(String name, Integer page, Integer size);

    void updatePassword(Long id, String bCryptPassword, String password);
}
