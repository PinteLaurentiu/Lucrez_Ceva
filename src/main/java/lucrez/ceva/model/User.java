package lucrez.ceva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import lucrez.ceva.model.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
@Entity(name = "user")
@Data
@ToString(exclude = {"userLogin", "userRoles"})
public class User implements Serializable {

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private UserLogin userLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UserRole> userRoles;
}
