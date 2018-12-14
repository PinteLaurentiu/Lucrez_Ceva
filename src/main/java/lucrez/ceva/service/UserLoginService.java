package lucrez.ceva.service;

import lombok.AllArgsConstructor;
import lucrez.ceva.model.UserLogin;
import lucrez.ceva.persistence.UserLoginRepo;
import lucrez.ceva.service.interfaces.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class UserLoginService implements IUserLoginService {
	private UserLoginRepo userLoginRepo;

	public List<UserLogin> findAll() {
		List<UserLogin> list = new ArrayList<>();
		userLoginRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userLoginRepo.delete(id);
	}

	@Override
    public UserLogin save(UserLogin user) {
		return userLoginRepo.save(user);
    }
}
