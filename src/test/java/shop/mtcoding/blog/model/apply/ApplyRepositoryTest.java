package shop.mtcoding.blog.model.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.model.comp.CompRepository;


@Import(ApplyRepository.class)
@DataJpaTest
public class ApplyRepositoryTest {
    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest(){
        int resumeId = 1;
        int jobsId = 3;


        String q = """
                INSERT INTO apply_tb (resume_id, jobs_id, status, created_at)
                VALUES ( ?, ?, 0, NOW())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,resumeId);
        query.setParameter(2,jobsId);
        query.executeUpdate();
    }
}
