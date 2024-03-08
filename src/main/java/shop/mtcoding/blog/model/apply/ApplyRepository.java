package shop.mtcoding.blog.model.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    public List<ApplyResponse.ApplyByJobsDTO> findAllByJobsId(Integer jobsId){
        String q = """
                select
                  at.id, ut.my_name, rt.title, rt.career
                from apply_tb at
                join resume_tb rt
                  on at.resume_id = rt.id
                join user_tb ut
                  on ut.id = rt.user_id
                where at.jobs_id = ?
                order by rt.id desc;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.ApplyByJobsDTO> result = mapper.list(query, ApplyResponse.ApplyByJobsDTO.class);
        return result;
    }

    public List<SkillRequest.ApplyskillDTO> findAllSkillById(Integer id){
        String q = """
                select
                 st.name, st.color
                from apply_tb at
                join resume_tb rt
                  on at.resume_id = rt.id
                join skill_tb st
                  on st.resume_id = rt.id
                where at.id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper mapper = new JpaResultMapper();
        return mapper.list(query, SkillRequest.ApplyskillDTO.class);
    }



    public void findById(){}

    @Transactional
    public void updateById(){}

    @Transactional
    public void save() {}

    @Transactional
    public void deleteById () {}
}
