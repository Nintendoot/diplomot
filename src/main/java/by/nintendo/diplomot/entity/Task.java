package by.nintendo.diplomot.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"project"})
@Entity
@Table(name = "task")
public class
Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;

@NotBlank
    @Column(name = "title")
    private String title;


    @Column(name = "description")
    private String description;

    @Column(name = "dateStart")
    private String dateStart;

    @Column(name = "dateEnd")
    private String dateEnd;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> usersTask;


}
