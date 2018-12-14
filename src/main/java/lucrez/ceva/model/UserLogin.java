package lucrez.ceva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "userLogin")
@Getter
@Setter
@NoArgsConstructor
public class UserLogin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String bcrypPassword;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(optional = false)
    @JsonIgnore
    private User user;

    private transient String password;
}
