package by.nintendo.diplomot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private LocalDateTime endTime;
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;

    @ManyToMany (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<User> users;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Task> tasks;

}
