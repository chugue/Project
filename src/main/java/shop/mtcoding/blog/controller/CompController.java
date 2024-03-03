package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.dto.user.UserRequest;
import shop.mtcoding.blog.model.comp.CompRepository;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {
    private final UserRepository userRepository;
    private final CompRepository compRepository;
    private final HttpSession session;

    @GetMapping("/comp/apply")
    public String apply() {
        return "/comp/apply";
    }

    @GetMapping("/comp/{id}/comphome")
    public String compHome(@PathVariable Integer id, HttpSession session) {
        //id값으로 공고리스트를 가져오고  배열

        List<Jobs> jobsList = compRepository.findAll(id);
        List<CompRequest.JobsViewDTO> viewDTOList = new ArrayList<>();
        List<SkillRequest.CompskillDTO> skills = new ArrayList<>();

        for (Jobs job : jobsList){
            //엔티티 -> DTO에 담기
//
//            for (String skill : job.getSkillList().sp(",")){
//                skills.add(SkillRequest.CompskillDTO.builder().name(skill).build());
//            }
//
//            viewDTOList.add()
        }

        for (int i = 0; i < jobsList.size(); i++) {

            // 1. JobList에 있는 문서하나 들고와서
            Jobs job = jobsList.get(i);
            // 2. ViewDTO를 생성한다
            CompRequest.JobsViewDTO viewDTO = new CompRequest.JobsViewDTO();
            viewDTO.setId(job.getId());
            viewDTO.setArea(job.getArea());
            viewDTO.setCareer(job.getCareer());
            viewDTO.setEdu(job.getEdu());
            viewDTO.setContent(job.getContent());
            viewDTO.setTask(job.getTask());
            viewDTO.setTitle(job.getTitle());
            viewDTO.setUser(job.getUser());
            viewDTO.setCreatedAt(job.getCreatedAt());
            viewDTO.setSkillList(job.getSkillList());
            viewDTO.setNumber(i + 1);
            // 3. ViewDTO를 ViewDTOList에 담는다
            viewDTOList.add(viewDTO);
        }






        session.setAttribute("jobList", viewDTOList);

        //리스트를 담는 ArrayList 생성
        //세션에 저장
        //머스테치에 뿌림

        return "/comp/comphome";
    }

    @GetMapping("/comp/joinForm")
    public String compJoinForm() {

        return "/comp/joinForm";
    }

    @PostMapping("/comp/join/{role}")
    public String compJoin(@PathVariable int role, UserRequest.UserAllDTO requestDTO){
        requestDTO.setRole(role);
        userRepository.save(requestDTO);
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
        return "redirect:/";
    }



    @GetMapping("/comp/profileUpdateForm")
    public String profileUpdateForm() {
        return "/comp/profileUpdateForm";
    }

    @GetMapping("/comp/readResume")
    public String readResume(HttpServletRequest request){

        request.setAttribute("scrap", true);
        return "/comp/readResume";
    }

    @GetMapping("/comp/scrap")
    public String scrap() {
        return "/comp/scrap";
    }

    @GetMapping("/comp/talent")
    public String talent() {
        return "/comp/talent";
    }

    @GetMapping("/comp/updateForm")
    public String updateForm() {
        return "/comp/updateForm";
    }

    @GetMapping("/comp/jobsInfo")
    public String jobsInfo() {

        return "/comp/jobsInfo";
    }

    @GetMapping("/comp/compResumeDetail")
    public String compResumeDetail() {
        return "/comp/compResumeDetail";
    }
}
