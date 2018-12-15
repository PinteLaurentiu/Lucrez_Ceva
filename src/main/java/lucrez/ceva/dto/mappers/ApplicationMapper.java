package lucrez.ceva.dto.mappers;

import lucrez.ceva.model.Application;
import lucrez.ceva.model.Job;
import lucrez.ceva.model.User;

public class ApplicationMapper {

    public static Application linkUserJob(User user, Job job){
        Application application = new Application();
        application.setJob(job);
        application.setUser(user);

        return application;
    }

}
