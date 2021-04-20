package by.nintendo.diplomot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$",
            message = "{user.phoneNumber.pattern}")
    private String phone;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min=4)
    @Column(name = "login")
    private String login;

    @NotBlank
    @Size(min=4)
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER,mappedBy = "owner")
    private List<Project> ownedProjects;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="project_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
