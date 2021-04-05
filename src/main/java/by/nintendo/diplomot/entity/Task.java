package by.nintendo.diplomot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(min = 4)
    private String name;
    @NotBlank
    @Size(min = 4)
    private float hours;
    @NotBlank
    private LocalDate dateStart;
    @NotBlank
    private LocalDate dateEnd;
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
    @ManyToOne(fetch=FetchType.LAZY)
    private Project project;
}
