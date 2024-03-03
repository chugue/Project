package shop.mtcoding.blog.model.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.dto.user.UserRequest;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public List<User> findAll(){
        Query query = em.createNativeQuery("select * from user_tb order by id desc", User.class);

        List<User> userList = query.getResultList();
        return userList;
    }

    public List<User> findByCompAll(){
        Query query = em.createNativeQuery("select * from user_tb where role = 2 order by id desc ", User.class);

        List<User> userList = query.getResultList();
        return userList;
    }


    public void findById(){}

    @Transactional
    public void updateById(){}

    @Transactional
    public void save(UserRequest.UserAllDTO requestDTO) {
        String q = """
                insert into user_tb(email, my_name,password,phone, address, birth, business_number,photo, comp_name,homepage,role,created_at)
                values (?,?,?,?,?,?,?,?,?,?,?,now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getEmail());
        query.setParameter(2, requestDTO.getMyName());
        query.setParameter(3, requestDTO.getPassword());
        query.setParameter(4, requestDTO.getPhone());
        query.setParameter(5, requestDTO.getAddress());
        query.setParameter(6, requestDTO.getBirth());
        query.setParameter(7, requestDTO.getBusinessNumber());
        query.setParameter(8, requestDTO.getPhoto());
        query.setParameter(9, requestDTO.getCompName());
        query.setParameter(10, requestDTO.getHomepage());
        query.setParameter(11, requestDTO.getRole());
        query.executeUpdate();
    }

    @Transactional
    public void deleteById () {}

    public User findByEmailAndPassword(UserRequest.LoginDTO requestDTO) {
        String q = """
                select * from user_tb where email = ? and password = ?
                """;
        Query query = em.createNativeQuery(q, User.class);
        query.setParameter(1,requestDTO.getEmail());
        query.setParameter(2,requestDTO.getPassword());
        User user;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }
}
