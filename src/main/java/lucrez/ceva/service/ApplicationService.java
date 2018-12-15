package lucrez.ceva.service;

import lombok.AllArgsConstructor;
import lucrez.ceva.model.Application;
import lucrez.ceva.persistence.ApplicationRepository;
import lucrez.ceva.service.interfaces.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class ApplicationService implements IApplicationService {

    private ApplicationRepository applicationRepository;

    @Override
    public void save(Application application) {
        applicationRepository.save(application);
    }
}
