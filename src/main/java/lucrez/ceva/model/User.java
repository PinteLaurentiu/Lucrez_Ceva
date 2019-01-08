package lucrez.ceva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lucrez.ceva.model.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String phone;
    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(nullable = false)
    private String avatarPath;
    @Column
    private Gender gender;
    @Column
    private String location;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTag> tags;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserLogin userLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserActivation activation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Job> jobs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Application> applications;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookmark", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<Job> bookmarks;

}
