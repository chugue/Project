package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.mtcoding.blog.model.comp.CompRepository;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {

    private  final CompRepository compRepository;
    private  final HttpSession session;

    @GetMapping("/comp/apply")
    public String apply(){
        return "/comp/apply";
    }

    @GetMapping("/comp/comphome/{compId}")
    public String compHome(@PathVariable Integer compId){
        //comp-id값으로 공고리스트를 가져오고  배열
        List<Jobs> jobsList = compRepository.findAll(compId);

        session.setAttribute("jobList",jobsList);

        //리스트를 담는 ArrayList 생성
        //세션에 저장
        //머스테치에 뿌림

        return "/comp/comphome";
    }

    @GetMapping("/comp/joinForm")
    public String compJoinForm(){
        return "/comp/joinForm";
    }

    @GetMapping("/comp/loginForm")
    public String compLoginForm(){
        return "/comp/loginForm";
    }

    @GetMapping("/comp/profileUpdateForm")
    public String profileUpdateForm(){
        return "/comp/profileUpdateForm";
    }

    @GetMapping("/comp/readResume")
    public String readResume(HttpServletRequest request){

        request.setAttribute("scrap", true);

        return "/comp/readResume";
    }

    @GetMapping("/comp/scrap")
    public String scrap(){
        return "/comp/scrap";
    }

    @GetMapping("/comp/talent")
    public String talent(){
        return "/comp/talent";
    }

    @GetMapping("/comp/updateForm")
    public String updateForm(){
        return "/comp/updateForm";
    }

    @GetMapping("/comp/jobsInfo")
    public String jobsInfo(){

        return "/comp/jobsInfo";
    }
    @GetMapping("/comp/compResumeDetail")
    public String compResumeDetail(){
        return "/comp/compResumeDetail";
    }
}
