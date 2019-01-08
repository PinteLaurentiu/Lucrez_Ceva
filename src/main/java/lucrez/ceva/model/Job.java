package lucrez.ceva.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lucrez.ceva.model.enums.JobAcceptanceType;
import lucrez.ceva.model.enums.JobType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity(name ="job")
@Getter
@Setter
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column
    @Temporal(value = TemporalType.DATE)
    private Date expireDate;

    @Column
    private JobType jobType;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobTag> tags;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Application> applications;

    @ManyToMany(mappedBy = "bookmarks")
    private Set<User> bookmarks;

    @Column
    private Double salary;

    @Column(nullable = false)
    private JobAcceptanceType acceptanceType;
}
