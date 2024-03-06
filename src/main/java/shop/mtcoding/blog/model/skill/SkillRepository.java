package shop.mtcoding.blog.model.skill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SkillRepository {
    private final EntityManager em;

    public List<Skill> findAllV2(int jobsId){
        Query query = em.createNativeQuery("select * from skill_tb where jobs_id = ?", Skill.class);
        query.setParameter(1, jobsId);
        return query.getResultList();
    }
    //

    public List<String> findALLNameByJobsId(int jobsId){
        Query query = em.createNativeQuery("select name from skill_tb where jobs_id = ?", String.class);
        query.setParameter(1, jobsId);
        return query.getResultList();
    }


}
