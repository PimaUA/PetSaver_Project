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
@SecondaryTables({
        @SecondaryTable(
                name = "authorities",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "username")
        ),
        @SecondaryTable(
                name = "users_info",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "username")
        )
})
public class UserInfo {
    @Id
    @Column(name = "username")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean isEnabled;
    @Column(name = "authority", table = "authorities")
    private String roles;

    @Column(name = "country_code",table = "users_info")
    private Country country;
    @Column(name="email",table = "users_info",unique = true)
    private String email;
    /*@Column(name = "registered_at",table = "users_info")
    private Timestamp dateTimeOfRegistry;*/

    public UserInfo() {
    }

    public UserInfo(String username, String password, boolean isEnabled, String roles) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public UserInfo(String username, String password, boolean isEnabled, String roles, Country country, String email) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
        this.country = country;
        this.email = email;
    }
}
