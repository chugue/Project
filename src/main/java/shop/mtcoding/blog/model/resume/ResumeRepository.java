package shop.mtcoding.blog.model.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {

    private final EntityManager em;


    public List<Resume> findAll() {
        String q = """
                select * from resume_tb order by id desc
                """;

        Query query = em.createNativeQuery(q, Resume.class);
        List<Resume> resumeList = query.getResultList();

        return resumeList;
    }

    public Resume findById(int id) {
        String q = """
                select * from resume_tb where id = ?
                """;
        Query query = em.createNativeQuery(q,Resume.class);
        query.setParameter(1, id);

        Resume resume = (Resume) query.getSingleResult();
        return resume;
    }


    @Transactional
    public void save() {
//        String q = """
//            insert into resume_tb(title, area, edu, career, introduce, port_link, is_public, created_at)
//            values (?,?,?,?,?,?,?,?, now());
//            """;
//        Query query = em.createNativeQuery(q);
//        query.setParameter(2, requestDTO.getTitle());
//        query.setParameter(3, requestDTO.getArea());
//        query.setParameter(4, requestDTO.getEdu());
//        query.setParameter(5, requestDTO.getCareer());
//        query.setParameter(6, requestDTO.getIntroduce());
//        query.setParameter(7, requestDTO.getPortLink());
//        query.setParameter(8, requestDTO.getIsPublic());
//        query.executeUpdate();
    }



    @Transactional
    public void deleteById() {

    }

//    public Resume findByUser(Integer id) {
//        String q = """
//                select r.*, u.my_name, u.birth, u.phone, u.email, u.address , u.id
//                from resume_tb r join user_tb u on r.user_id = u.id where r.id = ?;
//                """;
//
//        Query query = em.createNativeQuery(q, Resume.class);
//        query.setParameter(1,id);
//        Resume resume = (Resume) query.getSingleResult();
//
//        return resume;
//
//    }
}
