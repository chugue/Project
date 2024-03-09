package shop.mtcoding.blog.model.pass;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "pass_tb")
@Data
@Entity
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer resumeId;
    private Integer jobsId;
    private Integer status;
    private Timestamp createdAt;
}
