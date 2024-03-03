package shop.mtcoding.blog.model.jobs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JobsRepository {
    private final EntityManager em;

    public void findAll(){

    }
//    public Jobs findJobId(Integer id){
//        Query query = em.createNativeQuery("select user_id from jobs_tb where id = ?", Jobs.class);
//        query.setParameter(1,id);
//
//        Jobs job = (Jobs) query.getSingleResult();
//
//        return job;
//    }


    public Object[] findById(Integer jobId){
        String q = """
                 select
            ut.comp_name,
            ut.business_number,
            ut.phone,
            jt.area,
            jt.edu,
            jt.career,
            jt.content,
            jt.title,
            jt.id,
            ut.homepage,
            jt.task,
            jt.comp_id,
            jt.dead_line
        from jobs_tb jt
        join user_tb ut
            on jt.comp_id = ut.id
        where jt.id = ?
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1,jobId);
        Object[] job = (Object[]) query.getSingleResult();
        return job;
    }

    @Transactional
    public void updateById(){}

    @Transactional
    public void save(JobRequest.JobWriterDTO requestDTO) {
        Query query = em.createNativeQuery("insert into Jobs_tb(user_id,title,edu,area,career,content,dead_line,task,created_at) values(?,?,?,?,?,?,?,?,now())");
        query.setParameter(1,requestDTO.getUserId());
        query.setParameter(2,requestDTO.getTitle());
        query.setParameter(3,requestDTO.getEdu());
        query.setParameter(4,requestDTO.getArea());
        query.setParameter(5,requestDTO.getCareer());
        query.setParameter(6,requestDTO.getContent());
        query.setParameter(7,requestDTO.getDeadLine());
        query.setParameter(8,requestDTO.getTask());
        query.executeUpdate();
    }

    @Transactional
    public void update(JobRequest.JobUpdateDTO requestDTO){
        Query query = em.createNativeQuery("update Jobs_tb set title = ? ,area = ?,edu=?, career =? ,content = ?, dead_line = ? , task = ? where id = ?");
        query.setParameter(1,requestDTO.getTitle());
        query.setParameter(2,requestDTO.getArea());
        query.setParameter(3,requestDTO.getEdu());
        query.setParameter(4,requestDTO.getCareer());
        query.setParameter(5,requestDTO.getContent());
        query.setParameter(6,requestDTO.getDeadLine());
        query.setParameter(7,requestDTO.getTask());
        query.setParameter(8,requestDTO.getId());
        query.executeUpdate();
    }


    @Transactional
    public void deleteById (Integer compId,Integer jobId) {

        //스킬 테이블에 있는 jobId 찾아서 삭제
        Query skillDeleteQuery = em.createNativeQuery("delete from skill_tb where jobs_id = ?");
        skillDeleteQuery.setParameter(1,jobId);
        skillDeleteQuery.executeUpdate();

        Query jobDeleteQuery = em.createNativeQuery("delete from jobs_tb where user_id = ? AND id = ?");

        jobDeleteQuery.setParameter(1,compId);
        jobDeleteQuery.setParameter(2,jobId);
        jobDeleteQuery.executeUpdate();
    }
}
