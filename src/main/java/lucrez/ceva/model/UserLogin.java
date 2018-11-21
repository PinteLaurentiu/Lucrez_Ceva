package lucrez.ceva.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "userLogin")
@Data
public class UserLogin implements Serializable {

    public static final String QUERY_FIND_USER_ROLES_BY_USER_LOGIN = "QueryFindUserRolesByUserLogin";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String bcrypPassword="";

    @Column(nullable = false, unique = true)
    private String email;

//    @OneToOne(optional = false)
//    private User user;
}
