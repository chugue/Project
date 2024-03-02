package shop.mtcoding.blog.model.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class ResumeRepository {

    public void findAll(){
    }

    public void findById(){}

    @Transactional
    public void updateById(){}

    @Transactional
    public void save() {}

    @Transactional
    public void deleteById () {}

}
