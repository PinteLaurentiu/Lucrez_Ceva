package lucrez.ceva.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lucrez.ceva.model.enums.TipJob;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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
    private String descriere;

    @Column(nullable = false)
    private String titlu;

    @Column(nullable = false)
    private String locatie;

    @Column(nullable = false)
    private LocalDate data;

    @Column
    private LocalDate dataExpirarii;

    @Column
    private TipJob tipJob;

    @Column(name = "tags")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Application> applications;

    @ManyToMany(mappedBy = "bookmarks")
    private Set<User> users;

}
