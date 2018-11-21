package lucrez.ceva.service.interfaces;


import lucrez.ceva.model.UserLogin;

import java.util.List;

public interface IUserLoginService {

    UserLogin save(UserLogin user);
    List<UserLogin> findAll();
    void delete(long id);
}
