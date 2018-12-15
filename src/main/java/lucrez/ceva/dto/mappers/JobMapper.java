package lucrez.ceva.dto.mappers;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.JobDto;
import lucrez.ceva.model.Job;
import lucrez.ceva.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

@Component
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class JobMapper {

    public static Job dtoToModelJob(User user, JobDto jobDto){
        Job job = new Job();
        LocalDate date = LocalDate.now();

        job.setDescriere(jobDto.getDescriere());
        job.setTitlu(jobDto.getTitlu());
        job.setLocatie(jobDto.getLocatie());
        job.setData(date);
        job.setTipJob(jobDto.getTipJob());
        job.setDataExpirarii(addDays(date, jobDto.getNrZile()));
        job.setTags(new ArrayList<>(jobDto.getTags()));
        job.setUser(user);

        return job;
    }

    private static LocalDate addDays(LocalDate localDate, Long nrZile){
        return localDate.plusDays(nrZile);
    }

    public static void updateModelWithDto(Job job, JobDto jobDto) {
        job.setDescriere(jobDto.getDescriere());
        job.setTitlu(jobDto.getTitlu());
        job.setLocatie(jobDto.getLocatie());
        job.setTipJob(jobDto.getTipJob());
        job.setDataExpirarii(addDays(job.getData(), jobDto.getNrZile()));
        job.setTags(new ArrayList<>(jobDto.getTags()));
    }
}
