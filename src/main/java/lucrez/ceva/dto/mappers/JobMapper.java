package lucrez.ceva.dto.mappers;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.JobAddDto;
import lucrez.ceva.dto.JobDetailedDto;
import lucrez.ceva.dto.JobSimpleDto;
import lucrez.ceva.model.Job;
import lucrez.ceva.model.JobTag;
import lucrez.ceva.model.User;
import lucrez.ceva.model.enums.JobAcceptanceType;
import lucrez.ceva.service.staticService.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class JobMapper {

    public static Job dtoToModelJob(User user, JobAddDto jobAddDto){
        Job job = new Job();
        Date date = new Date();

        job.setDescription(jobAddDto.getDescription());
        job.setTitle(jobAddDto.getTitle());
        job.setLocation(jobAddDto.getLocation());
        job.setDate(date);
        job.setJobType(jobAddDto.getJobType());
        job.setExpireDate(DateTimeService.addDays(date, jobAddDto.getDays()));
        job.setTags(jobAddDto.getTags().stream().map(x->createTag(x, job)).collect(Collectors.toSet()));
        job.setUser(user);
        job.setApplications(new HashSet<>());
        job.setBookmarks(new HashSet<>());
        job.setSalary(jobAddDto.getSalary());
        job.setAcceptanceType(JobAcceptanceType.NotReviewed);

        return job;
    }

    public static void updateModelWithDto(Job job, JobAddDto jobAddDto) {
        job.setDescription(jobAddDto.getDescription());
        job.setTitle(jobAddDto.getTitle());
        job.setLocation(jobAddDto.getLocation());
        job.setJobType(jobAddDto.getJobType());
        job.setExpireDate(DateTimeService.addDays(job.getDate(), jobAddDto.getDays()));
        job.getTags().clear();
        job.getTags().addAll(jobAddDto.getTags().stream().map(x->createTag(x, job)).collect(Collectors.toSet()));
        job.setSalary(jobAddDto.getSalary());
    }

    private static JobTag createTag(String s, Job job) {
        JobTag tag = new JobTag();
        tag.setTag(s);
        tag.setJob(job);
        return tag;
    }

    public static List<JobSimpleDto> mapJobSimpleDto(Collection<Job> jobs, User current) {
        return jobs.stream().map((x)-> mapJobSimpleDto(x, current, new JobSimpleDto())).collect(Collectors.toList());
    }

    private static JobSimpleDto mapJobSimpleDto(Job job, User current, JobSimpleDto jobSimpleDto) {
        jobSimpleDto.setId(job.getId());
        jobSimpleDto.setLocation(job.getLocation());
        jobSimpleDto.setTitle(job.getTitle());
        jobSimpleDto.setType(job.getJobType());
        jobSimpleDto.setDaysSincePosted(DateTimeService.daysDifference(job.getDate(), new Date()));
        jobSimpleDto.setAcceptanceType(job.getAcceptanceType());
        if (current != null)
            setBookmarked(job, current, jobSimpleDto);
        return jobSimpleDto;
    }

    private static void setBookmarked(Job job, User current, JobSimpleDto jobSimpleDto) {
        jobSimpleDto.setBookmarked(false);
        for (Job bookmark : current.getBookmarks()) {
            if (bookmark.getId().equals(job.getId()))
                jobSimpleDto.setBookmarked(true);
        }
    }

    public static JobDetailedDto createJobDetailedDto(Job job, User user) {
        JobDetailedDto dto = new JobDetailedDto();
        mapJobSimpleDto(job, user, dto);
        dto.setDescription(job.getDescription());
        dto.setAvatarPath(job.getUser().getAvatarPath());
        dto.setUsername(job.getUser().getName());
        dto.setSalary(job.getSalary());
        dto.setTags(job.getTags().stream().map(JobTag::getTag).collect(Collectors.toList()));
        return dto;
    }
}
