package shop.mtcoding.blog.model.user;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "user_tb")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 이메일
    @Column(unique = true, nullable = false)
    private String email;
<<<<<<< HEAD

    // 유저 네임
=======
  
>>>>>>> 9dadc20184ba74d8a2b69eb438e65bbe5b7b8d3b
    @Column(nullable = false)
    private String myName;

    // 유저 비번
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String phone;
    private String address;
    private Date birth;

    @Column(unique = true)
    private String businessNumber;
    private String photo;
    private String compName;
    private String homepage;
    @Column(nullable = false)
    private Integer role;
    @Column(nullable = false)
    private Timestamp createdAt;

}

