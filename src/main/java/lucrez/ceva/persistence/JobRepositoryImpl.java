package lucrez.ceva.persistence;

import lombok.Setter;
import lucrez.ceva.model.Job;
import lucrez.ceva.model.JobFilter;
import lucrez.ceva.model.JobPageableFilter;
import lucrez.ceva.model.enums.JobAcceptanceType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
@Setter
public class JobRepositoryImpl implements JobRepositoryCustom {
    public static final String InnerSelectTagMatches = "(select count(jt) from job_tag jt where jt.job.id = j.id and jt.tag in :tags) as matches";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Job> getAllFiltered(JobFilter filter) {
        TypedQuery<Job> query = getJobQuery(filter);
        return query.getResultList();
    }

    @Override
    public List<Job> getAllFiltered(JobPageableFilter filter) {
        TypedQuery<Job> query = getJobQuery(filter);
        query.setFirstResult(filter.getPage() * filter.getPageSize());
        query.setMaxResults(filter.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<Job> getByTag(List<String> tags, JobFilter filter) {
        TypedQuery<Object[]> query = getJobWithTagsQuery(tags, filter);
        return query.getResultList().stream().map(x-> (Job)(x[0])).collect(Collectors.toList());
    }

    @Override
    public List<Job> getByTag(List<String> tags, JobPageableFilter filter) {
        TypedQuery<Object[]> query = getJobWithTagsQuery(tags, filter);
        query.setFirstResult(filter.getPage() * filter.getPageSize());
        query.setMaxResults(filter.getPageSize());
        return query.getResultList().stream().map(x-> (Job)(x[0])).collect(Collectors.toList());
    }

    private TypedQuery<Object[]> getJobWithTagsQuery(List<String> tags, JobFilter filter) {
        //noinspection JpaQlInspection
        TypedQuery<Object[]> query = entityManager.createQuery("select j," + InnerSelectTagMatches +
                " from job j where j.date < :now" +
                getFilterString(filter) +
                " order by matches desc, j.date desc", Object[].class);
        query.setParameter("now", new Date());
        query.setParameter("tags", tags);
        setFilterQueryParams(filter, query);
        return query;
    }

    private TypedQuery<Job> getJobQuery(JobFilter filter) {
        TypedQuery<Job> query = entityManager.createQuery("select j from job j where j.date < :now" +
                getFilterString(filter) +
                " order by j.date desc", Job.class);
        query.setParameter("now", new Date());
        setFilterQueryParams(filter, query);
        return query;
    }

    private <T> void setFilterQueryParams(JobFilter filter, TypedQuery<T> query) {
        if (!filter.getLocations().isEmpty())
            query.setParameter("locations", filter.getLocations());
        if (!filter.getTypes().isEmpty())
            query.setParameter("types", filter.getTypes());
        if (filter.getWord() != null && !filter.getWord().isEmpty() )
            query.setParameter("word", "%" + filter.getWord() + "%");
        if (filter.getJobAcceptanceType() != JobAcceptanceType.All)
            query.setParameter("acceptanceType", filter.getJobAcceptanceType());
    }

    private String getFilterString(JobFilter filter) {
        return (!filter.getLocations().isEmpty() ? " and j.location in :locations" : "") +
                (!filter.getTypes().isEmpty() ? " and j.jobType in :types" : "") +
                (filter.getWord() != null && !filter.getWord().isEmpty() ? " and (j.description like :word or j.title like :word)" : "") +
                (filter.getJobAcceptanceType() != JobAcceptanceType.All ? " and j.acceptanceType = :acceptanceType" : "");
    }
}
