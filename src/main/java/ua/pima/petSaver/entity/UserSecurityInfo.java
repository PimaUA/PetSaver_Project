package ua.pima.petSaver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserSecurityInfo {
    @Id
    @Size(min=3)
    private String username;
    @NotBlank
    private String password;
}
