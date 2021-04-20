package by.nintendo.diplomot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "short_name")
    private String shortName;

    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private String creatTime;

    @Column(name = "end_time")
    private String endTime;

    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name="project_users",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Task> tasks;

}
