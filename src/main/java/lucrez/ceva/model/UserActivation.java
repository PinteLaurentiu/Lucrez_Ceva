package lucrez.ceva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "user_activation")
@Getter
@Setter
@NoArgsConstructor
public class UserActivation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String uuid;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

    @Column(nullable = false)
    private boolean activated;
}
