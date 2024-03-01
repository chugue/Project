package shop.mtcoding.blog.model.resume;


import jakarta.persistence.*;
import lombok.Data;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "resume_tb")
@Data
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Skill> skillList = new ArrayList<>();

    @Column(nullable = false)
    private String title;
    // 희망 근무 지역
    private String area;

    @Column(nullable = false)
    private String edu;

    @Column(nullable = false)
    private String career;

    // 자기소개
    @Column(nullable = false)
    private String introduce;
    // 포트폴리오 링크
    private String portLink;

    @Column(nullable = false)
    private Boolean isPublic;

    @Column(nullable = false)
    private Timestamp createdAt;

}
