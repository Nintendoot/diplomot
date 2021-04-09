package by.nintendo.diplomot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;
    @NotBlank
    @Size(min = 4)
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @NotBlank
    @Size(min = 4)
    private float hours;
    @NotBlank
    @Column(name = "dateStart")
    private LocalDate dateStart;
    @NotBlank
    @Column(name = "dateEnd")
    private LocalDate dateEnd;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projects;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToMany
    @JoinTable(name = "users_tasks",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
//    mappedBy = "task_id"
    @OneToMany
    private List<Comments> comments;


}
