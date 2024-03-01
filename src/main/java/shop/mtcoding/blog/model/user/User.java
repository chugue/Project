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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String myName;

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
