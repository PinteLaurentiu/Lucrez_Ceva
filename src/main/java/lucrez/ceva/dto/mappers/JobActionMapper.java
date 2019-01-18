package lucrez.ceva.dto.mappers;

import lucrez.ceva.dto.JobActionDto;
import lucrez.ceva.dto.JobSimpleDto;
import lucrez.ceva.model.JobAction;
import lucrez.ceva.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class JobActionMapper {

    private static JobActionDto toDto(JobAction action, User user) {
        JobActionDto dto = new JobActionDto();
        dto.setAction(action.getAction());
        dto.setActionDate(action.getDate());
        dto.setJob(JobMapper.mapJobSimpleDto(action.getJob(), user, new JobSimpleDto()));
        return dto;
    }

    public static List<JobActionDto> toDto(List<JobAction> action, User user) {
        return action.stream().map(x->toDto(x, user)).collect(Collectors.toList());
    }
}
