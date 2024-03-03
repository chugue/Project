package shop.mtcoding.blog.model.comp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CompRepository {
    private final EntityManager em;


    public List<Jobs> findAll(Integer id){
           Query query = em.createNativeQuery("select * from jobs_tb where user_id = ?;", Jobs.class);
           query.setParameter(1,id);


           List<Jobs> jobsList = query.getResultList();

           return jobsList;
    }

    public void findById(){

    }

    @Transactional
    public void updateById(){}

    @Transactional
    public void save() {}

    @Transactional
    public void deleteById () {}
}
