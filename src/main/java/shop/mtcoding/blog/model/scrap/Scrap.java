package shop.mtcoding.blog.model.scrap;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Table(name="scrap_tb", uniqueConstraints = {
        @UniqueConstraint(
                name="scrap_uk",
                columnNames={"resume_id","jobs_id"}
        )})

@Data
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer resumeId;
    private Integer jobsId;

    @Column(nullable = false)
    private Timestamp createdAt;
}
