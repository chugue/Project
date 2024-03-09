package shop.mtcoding.blog.model.pass;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class PassRepository {
    private final EntityManager em;


    public PassRequest.PassResumeJobsDTO findByIdResumeJobs(Integer id, Integer jobsId) {
        String q = """
                SELECT r.*, p.status, j.id
                FROM resume_tb r
                JOIN pass_tb p ON r.id = p.resume_id
                JOIN jobs_tb j ON p.jobs_id = j.id
                WHERE r.id = ? AND j.id = ?
                """;
        Query query = em.createNativeQuery(q, PassRequest.PassResumeJobsDTO.class);
        query.setParameter(1,id);
        query.setParameter(2,jobsId);
        PassRequest.PassResumeJobsDTO requestDTO = null;
        try {
            requestDTO = (PassRequest.PassResumeJobsDTO) query.getSingleResult();
        } catch (NoResultException e) {
            requestDTO.setStatus(0);
        }

        return requestDTO;
    }
}
