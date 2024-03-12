package shop.mtcoding.blog.model.apply;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.jobs.JobRequest;
import shop.mtcoding.blog.model.offer.OfferRequest;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.UserRequest;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class ApplyRepository {
    private final EntityManager em;

    public List<ApplyRequest.ApplyResumeJobsDTO2> findAllByJobsIdWithApply(Integer resumeId) {
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
            requestDTO.setIsPass("없음");
        }

        return requestDTO;
    }


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


    public void findByUserId(int userId){}

    @Transactional
    public void updateById(){}

    @Transactional
    public void save(ApplyRequest.saveDTO requestDTO) {
        String q = """
                insert into apply_tb(resume_id, jobs_id, status, created_at) values(?,?,0, now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getJobsId());
        query.executeUpdate();
    }

    @Transactional
    public void deleteById () {}



    @Transactional
    public void passUpdate(Integer id, Integer jobsId) {
        String q = """
                update apply_tb set is_pass = '합격' where jobs_id = ? and resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);
        query.setParameter(2, id);
        query.executeUpdate();
    }

    @Transactional
    public void failUpdate(Integer id, Integer jobsId) {
        String q = """
                update apply_tb set is_pass = '불합격' where jobs_id = ? and resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);
        query.setParameter(2, id);
        query.executeUpdate();
    }


    public Object findStatusAllOrNot (Integer userId, Integer jobId){
        String q = """
                SELECT a.is_pass
                FROM user_tb u
                JOIN apply_tb a ON u.id = a.user_id  
                JOIN jobs_tb j ON a.jobs_id = j.id
                WHERE user_id = ? and jobs_id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, jobId);
        return query.getSingleResult();


    }


    @Transactional
    public void saveResumeJobsApply(Integer resumeId, Integer jobsId) {
        String q = """
                INSERT INTO apply_tb (resume_id, jobs_id, is_pass, created_at)
                VALUES ( ?, ?, '대기중', NOW())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,resumeId);
        query.setParameter(2,jobsId);
        query.executeUpdate();


    }


    public List<Object[]> findStatusByResumeJobs(Integer resumeId, Integer jobsId) {
        String q = """
                SELECT r.id, j.*, a.is_pass
                FROM resume_tb r 
                JOIN apply_tb a ON r.id = a.resume_id
                JOIN jobs_tb j ON a.jobs_id = j.id
                WHERE resume_id = ? and jobs_id = ? 
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, jobsId);
        return query.getResultList();
    }
}
