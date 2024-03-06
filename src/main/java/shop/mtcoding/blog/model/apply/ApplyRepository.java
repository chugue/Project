package shop.mtcoding.blog.model.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    public List<Object[]> findAllByJobsId(Integer jobsId){
        String q = """
                select
                  at.id, ut.my_name, rt.title, rt.career, st.name
                from apply_tb at
                join resume_tb rt
                  on at.resume_id = rt.id
                join skill_tb st
                  on st.resume_id = rt.id
                join user_tb ut
                  on ut.id = rt.user_id
                where at.jobs_id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);
        return (List<Object[]>) query.getResultList();
    }

    public void findById(){}

    @Transactional
    public void updateById(){}

    @Transactional
    public void save() {}

    @Transactional
    public void deleteById () {}
}
