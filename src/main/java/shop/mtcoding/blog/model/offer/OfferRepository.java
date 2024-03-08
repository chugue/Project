package shop.mtcoding.blog.model.offer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.dto.scrap.ScrapRequest;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;
import shop.mtcoding.blog.model.apply.ApplyResponse;
import shop.mtcoding.blog.model.jobs.JobResponse;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OfferRepository {
    private final EntityManager em;

    public List<OfferRequest.CompOfterDTO> findAllByJobsId(Integer jobsId){
        String q = """
                select
                     ot.id, ut.my_name, rt.title, ot.status
                     from offer_tb ot
                     join resume_tb rt
                     on ot.resume_id = rt.id
                     join user_tb ut
                     on ut.id = rt.user_id
                     join jobs_tb jt
                     on jt.id = ot.jobs_id
                     where ot.jobs_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<OfferRequest.CompOfterDTO> result = mapper.list(query,OfferRequest.CompOfterDTO.class);
        return result;
    }

    public List<JobResponse.JobListByUserId> findAllByUserId(Integer id) {

        String q = """
                select
                jt.id as user_id, ut.comp_name, jt.title, jt.task, jt.career
                from jobs_tb jt
                join user_tb ut
                on jt.user_id = ut.id
                where ut.id = ?
                    """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper mapper = new JpaResultMapper();
        List<JobResponse.JobListByUserId> jobList = mapper.list(query,JobResponse.JobListByUserId.class);

        return jobList;
    }

    public List<SkillRequest.JobSkillDTO> findAllSkillById(Integer id){
        String q = """
               select
               st.name,st.color
               from jobs_tb jt
               join user_tb ut
               on jt.user_id = ut.id
               join skill_tb st
               on st.jobs_id = jt.id
               where jt.id =?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,id);

        JpaResultMapper mapper = new JpaResultMapper();
        return mapper.list(query,SkillRequest.JobSkillDTO.class);

    }
    @Transactional
    public void save(OfferRequest.SaveDTO requestDTO, Integer status) {
        String q = """
                insert into offer_tb(jobs_id, resume_id, status, created_at) values(?,?,?, now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getJobsId());
        query.setParameter(2, requestDTO.getResumeId());
        query.setParameter(3, status);
        query.executeUpdate();
    }

    public OfferResponse.OfferDetailDTO findOffer(Integer resumeId) {
        String q = """
                SELECT * FROM offer_tb WHERE resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);

        Integer id = 0;
        Boolean isScrap = false;

        System.out.println("offerId : " + id);
        System.out.println("isOffer : " + isScrap);

        OfferResponse.OfferDetailDTO responseDTO = new OfferResponse.OfferDetailDTO(
                id, isScrap
        );

        return responseDTO;
    }

    public OfferResponse.OfferDetailDTO findOffer(Integer resumeId, Integer sessionUserId) {
        String q = """
                SELECT id, 
                case when jobs_id is null then false else true 
                end as isOffer From offer_tb 
                where resume_id = ? and jobs_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, sessionUserId);

        Integer id = null;
        Boolean isScrap = null;
        try {
            Object[] row = (Object[]) query.getSingleResult();
            id = (Integer) row[0];
            isScrap = (Boolean) row[1];
        } catch (Exception e) {
            id = 0;
            isScrap = false;
        }

        System.out.println("offerId : " + id);
        System.out.println("isOffer : " + isScrap);

        OfferResponse.OfferDetailDTO responseDTO = new OfferResponse.OfferDetailDTO(
                id, isScrap
        );

        return responseDTO;
    }
}
