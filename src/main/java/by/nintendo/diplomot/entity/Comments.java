package by.nintendo.diplomot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private long id;
//
//    @Column(name = "text")
//    private String text;
//
//    @Column(name = "created_at")
//    private String createdAt;
//
//    @ManyToOne
//    @JoinColumn(name = "task_id")
//    private Task task;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
