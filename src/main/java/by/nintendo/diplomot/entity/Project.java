package by.nintendo.diplomot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @NotBlank
    @Size(min = 4)
    @Column(name = "title")
    private String title;
    @NotBlank
    @Size(min = 4,max = 6)
    @Column(name = "short_name")
    private String shortName;
    @NotBlank
    @Size(min = 10)
    @Column(name = "description")
    private String description;
    @Column(name = "create_time")
    private LocalDateTime creatTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @ManyToMany
    @JoinTable(name = "users_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
//    mappedBy="project_id",
    @OneToMany(cascade=CascadeType.ALL)
    private List<Task> tasks;

}
