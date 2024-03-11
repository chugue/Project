package shop.mtcoding.blog.model.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.offer.OfferRequest;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    public List<ApplyResponse.ApplyByJobsDTO> findAllByJobsId(Integer jobsId){
        String q = """
                select
                  rt.id, ut.my_name, rt.title, rt.career, at.jobs_id
                from apply_tb at
                join resume_tb rt
                  on at.resume_id = rt.id
                join user_tb ut
                  on ut.id = rt.user_id
                where at.jobs_id = ?;
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
    public ApplyRequest.JobsIdAndResumeIdDTO findAllByJobsIdAndResumeId(Integer jobsId, Integer resumeId) {
        String q = """
                select is_pass from apply_tb where jobs_id = ? and resume_id = ?;
                """;

        Query query = em.createNativeQuery(q, Apply.class);
        query.setParameter(1, jobsId);
        query.setParameter(2, resumeId);

        ApplyRequest.JobsIdAndResumeIdDTO apply = (ApplyRequest.JobsIdAndResumeIdDTO) query.getSingleResult();

        return apply;
    }


    public void findById(){}

    @Transactional
    public void updateById(){}

    @Transactional
    public void save(ApplyRequest.saveDTO requestDTO) {
        String q = """
                insert into apply_tb(resume_id, jobs_id, is_pass, created_at) values(?,?,?, now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getJobsId());
        query.setParameter(3, requestDTO.getIsPass());
        query.executeUpdate();
    }

    @Transactional
    public void deleteById () {}


    @Transactional
    public void passUpdate(Integer id, Integer jobsId) {
        String q = """
                update apply_tb set is_pass = '불합격' where jobs_id = ? and resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);
        query.setParameter(2, id);
        query.executeUpdate();
    }

    @Transactional
    public void failUpdate(Integer id, Integer jobsId) {
        String q = """
                update apply_tb set is_pass = '합격' where jobs_id = ? and resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);
        query.setParameter(2, id);
        query.executeUpdate();
    }
}
