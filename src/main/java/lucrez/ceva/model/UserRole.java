//package lucrez.ceva.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//import lombok.ToString;
//import lucrez.ceva.model.enums.Role;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity(name = "userRole")
//@Data
//@ToString(exclude = "user")
//public class UserRole implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private long id;
//
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
//
//    @Column(nullable = false)
//    private Role role;
//}
