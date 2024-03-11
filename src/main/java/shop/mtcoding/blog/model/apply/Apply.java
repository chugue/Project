package shop.mtcoding.blog.model.apply;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "apply_tb")
@Data
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer resumeId;

    @Column(nullable = false)
    private Integer jobsId;
    // 유저 상태값 (1 : 대기중) (2 : 확인)  (3 : 탈락)
    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Timestamp createdAt;

}
