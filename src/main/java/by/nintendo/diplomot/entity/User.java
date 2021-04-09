package by.nintendo.diplomot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotBlank
    @Size(min=2)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min=5)
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Column(name = "phone")
    private String phone;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min=4)
    @Column(name = "login")
    private String login;

    @NotBlank
    @Size(min=4)
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
