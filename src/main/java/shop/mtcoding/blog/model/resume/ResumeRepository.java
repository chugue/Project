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

    private final EntityManager em;

    public List<Resume> findResumeByUserId(Integer id) {
        String q = """
                select * from resume_tb where user_id = ?
                """;

        Query query = em.createNativeQuery(q, Resume.class);
        query.setParameter(1, id);
        List<Resume> resumeList = query.getResultList();

        return resumeList;
    }

    public List<Resume> findAll() {
        String q = """
                select * from resume_tb order by id desc
                """;

        Query query = em.createNativeQuery(q, Resume.class);
        List<Resume> resumeList = query.getResultList();

        return resumeList;
    }


    public List<Object[]> findAll(Integer userId) {


       String q = """
               SELECT r.id, r.user_Id, r.title, r.edu, r.area, s.resume_Id,r.career, s.name
               FROM resume_tb r
               inner join user_tb u
               ON r.user_id = u.id
               inner join skill_tb s
               on r.id = s.resume_id
               where u.id = ?;
               """;


        Query query = em.createNativeQuery(q);

        query.setParameter(1, userId);

        List<Object[]> resumeList = query.getResultList();

        return resumeList;
    }

    public Resume findById(Integer id) {
        String q = """
                select * from resume_tb where id = ?
                """;
        Query query = em.createNativeQuery(q, Resume.class);
        query.setParameter(1, id);

        Resume resume = (Resume) query.getSingleResult();
        return resume;
    }


    // 탬플릿에서 유저 못찾고 있는데 ..
    @Transactional
    public void save(ResumeRequest.ResumeWriterDTO requestDTO, Integer id) {
        String q = """
                insert into resume_tb(user_id ,title, area, edu, career, introduce, port_link, created_at) 
                values (?,?,?,?,?,?,?, now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getArea());
        query.setParameter(4, requestDTO.getEdu());
        query.setParameter(5, requestDTO.getCareer());
        query.setParameter(6, requestDTO.getIntroduce());
        query.setParameter(7, requestDTO.getPortLink());

        query.executeUpdate();
    }


    // yz/0305 이력서 삭제부분
    @Transactional
    public void deleteById(Integer id) {
        Query query = em.createNativeQuery("delete from resume_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // yz/0305 이력서 수정부분
    @Transactional
    public void updateById(ResumeRequest.ResumeUpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update resume_tb set area = ?, career = ?, edu = ?, introduce = ?, port_link = ?, title = ? where id = ?");
        query.setParameter(1, requestDTO.getArea());
        query.setParameter(2, requestDTO.getCareer());
        query.setParameter(3, requestDTO.getEdu());
        query.setParameter(4, requestDTO.getIntroduce());
        query.setParameter(5, requestDTO.getPortLink());
        query.setParameter(6, requestDTO.getTitle());
        query.setParameter(7, id);

        query.executeUpdate();
        System.out.println("query: " + query);

    }
}