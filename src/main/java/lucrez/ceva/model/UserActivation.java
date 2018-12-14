package lucrez.ceva.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "userActivation")
@Getter
@Setter
@NoArgsConstructor
public class UserActivation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private String uuid;

    @Column
    private LocalDateTime expiration;

    @Column(nullable = false)
    private boolean activated;
}
