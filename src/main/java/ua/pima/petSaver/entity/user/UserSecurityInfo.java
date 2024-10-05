package ua.pima.petSaver.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Setter
@Getter
@Entity
@Table(name = "users")
@SecondaryTable(name = "authorities", pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
public class UserSecurityInfo {
    @Id
    @Column(name = "username")
    //??
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    boolean enabled;
    @Column(name = "authority", table = "authorities")
    private String roles;

    public UserSecurityInfo() {
    }

    public UserSecurityInfo(String username, String password, boolean enabled, String roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }
}
