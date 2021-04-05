package by.nintendo.diplomot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(min = 4)
    private String name;
    @NotBlank
    @Size(min = 4,max = 6)
    private String shortName;
    @NotBlank
    @Size(min = 10)
    private String description;
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL)
    private List<Task> tasks;

}
