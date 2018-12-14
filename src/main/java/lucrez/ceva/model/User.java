package lucrez.ceva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lucrez.ceva.model.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String phone;
    @Column
    private LocalDate birthday;
    @Column(nullable = false)
    private String avatarPath;
    @Column
    private Gender gender;
    @Column
    private String location;
    @Column(length = 10000, nullable = false)
    private String abilities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AbilityTag> tags;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserLogin userLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserActivation activation;
}
