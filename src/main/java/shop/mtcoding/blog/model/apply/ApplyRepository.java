package shop.mtcoding.blog.model.apply;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.UserRequest;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;


    public ApplyRequest.ApplyResumeJobsDTO findByIdResumeJobs(Integer id, Integer jobsId) {
        String q = """
                SELECT r.*, p.status, j.id
                FROM resume_tb r
                JOIN pass_tb p ON r.id = p.resume_id
                JOIN jobs_tb j ON p.jobs_id = j.id
                WHERE r.id = ? AND j.id = ?
                """;
        Query query = em.createNativeQuery(q, ApplyRequest.ApplyResumeJobsDTO.class);
        query.setParameter(1,id);
        query.setParameter(2,jobsId);
        ApplyRequest.ApplyResumeJobsDTO requestDTO = null;
        try {
            requestDTO = (ApplyRequest.ApplyResumeJobsDTO) query.getSingleResult();
        } catch (NoResultException e) {
            requestDTO.setStatus(0);
        }

        return requestDTO;
    }


    public List<ApplyResponse.ApplyByJobsDTO> findAllByJobsId(Integer jobsId){
        String q = """
                select
                  at.id, ut.my_name, rt.title, rt.career, at.jobs_id
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

    @Transactional
    public void saveResumeJobsApply(Integer resumeId, Integer jobsId) {
        String q = """
                INSERT INTO apply_tb (resume_id, jobs_id, status, created_at)
                VALUES ( ?, ?, 0, NOW())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,resumeId);
        query.setParameter(2,jobsId);
        query.executeUpdate();

    }

    public Object[] findStatusByResumeJobs(Integer resumeId, Integer jobsId) {
        String q = """
                SELECT r.id, j.*, a.status
                FROM resume_tb r 
                JOIN apply_tb a ON r.id = a.resume_id
                JOIN jobs_tb j ON a.jobs_id = j.id
                WHERE resume_id = ? and jobs_id = ? 
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, jobsId);
        return (Object[]) query.getSingleResult();
    }

    public List<ApplyRequest.ApplyResumeJobsDTO2> findAllByResumeId(Integer resumeId){
        String q = """
                    SELECT j.*, r.id, a.status
                    FROM jobs_tb j
                    JOIN apply_tb a ON j.id = a.jobs_id
                    JOIN resume_tb r ON a.resume_id = r.id
                    WHERE resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyRequest.ApplyResumeJobsDTO2> result = mapper.list(query, ApplyRequest.ApplyResumeJobsDTO2.class);
        return result;
    }
}
