package shop.mtcoding.blog.model.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {

<<<<<<< HEAD
    public void findAll(){
=======
    private final EntityManager em;

    public List<Resume> findAll() {
        String q = """
                    select * from resume_tb order by id desc
                    """;

//        String q = """
//                SELECT r.id, r.title, r.edu, r.career, r.area, s.id, s.resume_id, s.name
//                FROM resume_tb r
//                LEFT OUTER JOIN skill_tb s ON (r.id = s.resume_id)
//                ORDER BY r.id DESC;
//                """;

        Query query = em.createNativeQuery(q, Resume.class);
        List<Resume> resumeList = query.getResultList();

        return resumeList;
>>>>>>> 9dadc20184ba74d8a2b69eb438e65bbe5b7b8d3b
    }

    public void findById() {
    }

    @Transactional
    public void updateById() {
    }

    @Transactional
    public void save() {
    }

    @Transactional
    public void deleteById() {
    }

}
