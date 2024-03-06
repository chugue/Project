package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;
import shop.mtcoding.blog.dto.user.UserRequest;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.apply.ApplyResponse;
import shop.mtcoding.blog.model.comp.CompRepository;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.scrap.Scrap;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.dto.scrap.ScrapRequest;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.skill.Skill;
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
    private final JobsRepository jobsRepository;
    private final ScrapRepository scrapRepository;
    private final ApplyRepository applyRepository;
    private final HttpSession session;


    private final ResumeRepository resumeRepository;

    @GetMapping("/comp/{id}/apply")
    public String apply(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionComp");
        request.setAttribute("id", sessionUser.getId());

        return "/comp/apply";
    }

    @GetMapping("/comp/{id}/comphome")
    public String compHome(@PathVariable Integer id, @RequestParam(required = false, defaultValue ="1" ) Integer jobsId, HttpServletRequest request) {

        // System.out.println(jobsId);
        List<Object[]> objects = applyRepository.findAllByJobsId(jobsId);
        List<ApplyResponse.ApplyByJobsDTO> applyByJobsDTOList = new ArrayList<>();

        ApplyResponse.ApplyByJobsDTO prevDTO = new ApplyResponse.ApplyByJobsDTO();
        for (int i = 0; i < objects.size(); i++) {
            String color = "";
            Object[] object = objects.get(i);
            if (prevDTO.getId() == object[0]){
                //스킬 담는 DTO생성
                //네임이랑 컬러 추가
                //스킬리스트에 추가

                if (((String)object[4]).equals("jQuery")){
                    color = "badge bg-primary";

                }
                else if(((String)object[4]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)object[4]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)object[4]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)object[4]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)object[4]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)object[4]).equals("React")){
                    color = "badge bg-dark";
                }

                SkillRequest.ApplyskillDTO skill = new SkillRequest.ApplyskillDTO();
                skill.setName((String) object[4]);

                skill.setColor(color);
                prevDTO.getSkillList().add(skill);

            }else{

                if (((String)object[4]).equals("jQuery")){
                    color = "badge bg-primary";
                }
                else if(((String)object[4]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)object[4]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)object[4]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)object[4]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)object[4]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)object[4]).equals("React")){
                    color = "badge bg-dark";
                }

                ApplyResponse.ApplyByJobsDTO newApplyByJobsDTO = new ApplyResponse.ApplyByJobsDTO();

                SkillRequest.ApplyskillDTO skill = new SkillRequest.ApplyskillDTO();
                List<SkillRequest.ApplyskillDTO> skillList = new ArrayList<>();

                skill.setName((String) object[4]);
                skill.setColor(color);

                skillList.add(skill);

                newApplyByJobsDTO.setId((Integer) object[0]);
                newApplyByJobsDTO.setMyName((String) object[1]);
                newApplyByJobsDTO.setTitle((String) object[2]);
                newApplyByJobsDTO.setCareer((String) object[3]);
                newApplyByJobsDTO.setSkillList(skillList);

                applyByJobsDTOList.add(newApplyByJobsDTO);
                prevDTO = newApplyByJobsDTO;
            }

        }

        request.setAttribute("compResumeList",applyByJobsDTOList);
        //id값으로 공고리스트를 가져오고  배열

        List<Object[]> jobsList = jobsRepository.findAllByUserId(id);
        List<CompRequest.JobsViewDTO> viewDTOList = new ArrayList<>();

        Integer nextNumber = 1;
        CompRequest.JobsViewDTO prevViewDTO = new CompRequest.JobsViewDTO();

        for (int i = 0; i < jobsList.size(); i++) {

            Object[] job = jobsList.get(i);
            if(prevViewDTO.getId() == job[0]){
                // 스킬 색깔 생성
                String color = "";
                if (((String)job[6]).equals("jQuery")){
                    color = "badge bg-primary";

                }
                else if(((String)job[6]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)job[6]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)job[6]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)job[6]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)job[6]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)job[6]).equals("React")){
                    color = "badge bg-dark";
                }

                SkillRequest.CompskillDTO skillDTO = SkillRequest.CompskillDTO.builder().name((String) job[6]).color(color).build();

                // 이전에 있던 viewDOT.skillList에 add
                prevViewDTO.getSkillList().add(skillDTO);

            }else{
                // 스킬 리스트 생성
                List<SkillRequest.CompskillDTO> skillList = new ArrayList<>();

                String color = "";
                if (((String)job[6]).equals("jQuery")){
                    color = "badge bg-primary";

                }
                else if(((String)job[6]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)job[6]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)job[6]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)job[6]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)job[6]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)job[6]).equals("React")){
                    color = "badge bg-dark";
                }

                // 스킬 이름 set
                skillList.add(SkillRequest.CompskillDTO.builder().name((String) job[6]).color(color).build());

                // 새로운 DTO 생성
                prevViewDTO = new CompRequest.JobsViewDTO();

                prevViewDTO.setId((Integer) job[0]);
                prevViewDTO.setUserId((Integer) job[1]);
                prevViewDTO.setCompName((String) job[2]);
                prevViewDTO.setTitle((String) job[3]);
                prevViewDTO.setTask((String) job[4]);
                prevViewDTO.setCareer((String) job[5]);
                prevViewDTO.setSkillList(skillList);
                prevViewDTO.setNumber(nextNumber++);
                viewDTOList.add(prevViewDTO);
            }

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
    public String readResume(){
        List<Object[]> resumeViewList = compRepository.findAllByUserId();
        List<CompRequest.ResumeViewDTO> viewDTOList = new ArrayList<>();// 담는리스트
        CompRequest.ResumeViewDTO prevViewDTO = new CompRequest.ResumeViewDTO(); // 담는 로우하나

        for (int i = 0; i < resumeViewList.size(); i++) {

            Object[] job = resumeViewList.get(i);
            if(prevViewDTO.getId() == job[0]){
                // 스킬 색깔 생성
                String color = "";
                if (((String)job[7]).equals("jQuery")){
                    color = "badge bg-primary";
                }
                else if(((String)job[7]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)job[7]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)job[7]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)job[7]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)job[7]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)job[7]).equals("React")){
                    color = "badge bg-dark";
                }

                SkillRequest.CompskillDTO skillDTO = SkillRequest.CompskillDTO.builder().name((String) job[7]).color(color).build();

                // 이전에 있던 viewDOT.skillList에 add
                prevViewDTO.getSkillList().add(skillDTO);
            }else{
                // 스킬 리스트 생성
                List<SkillRequest.CompskillDTO> skillList = new ArrayList<>();

                String color = "";
                if (((String)job[7]).equals("jQuery")){
                    color = "badge bg-primary";
                }
                else if(((String)job[7]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)job[7]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)job[7]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)job[7]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)job[7]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)job[7]).equals("React")){
                    color = "badge bg-dark";
                }

                // 스킬 이름 set
                skillList.add(SkillRequest.CompskillDTO.builder().name((String) job[7]).color(color).build());

                // 새로운 DTO 생성
                prevViewDTO = new CompRequest.ResumeViewDTO();
                prevViewDTO.setId((Integer) job[0]);
                prevViewDTO.setUserId((Integer) job[1]);
                prevViewDTO.setMyName((String) job[2]);
                prevViewDTO.setTitle((String) job[3]);
                prevViewDTO.setEdu((String) job[4]);
                prevViewDTO.setCareer((String) job[5]);
                prevViewDTO.setArea((String) job[6]);
                prevViewDTO.setSkillList(skillList);
                viewDTOList.add(prevViewDTO);
            }

        }

        System.out.println(viewDTOList);

        session.setAttribute("readResumeList", viewDTOList);


        return "/comp/readResume";
    }


    @GetMapping("/comp/{id}/scrap")
    public String scrap(@PathVariable Integer id, HttpServletRequest request) {

        List<Scrap> scrapList = scrapRepository.findByUserId(id);

        request.setAttribute("scrapList", scrapList);

        List<Object[]> resumeViewList = compRepository.findAllByUserId();
        List<CompRequest.ResumeViewDTO> viewDTOList = new ArrayList<>();// 담는리스트
        CompRequest.ResumeViewDTO prevViewDTO = new CompRequest.ResumeViewDTO(); // 담는 로우하나

        for (int i = 0; i < resumeViewList.size(); i++) {

            Object[] job = resumeViewList.get(i);
            if(prevViewDTO.getId() == job[0]){
                // 스킬 색깔 생성
                String color = "";
                if (((String)job[7]).equals("jQuery")){
                    color = "badge bg-primary";
                }
                else if(((String)job[7]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)job[7]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)job[7]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)job[7]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)job[7]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)job[7]).equals("React")){
                    color = "badge bg-dark";
                }

                SkillRequest.CompskillDTO skillDTO = SkillRequest.CompskillDTO.builder().name((String) job[7]).color(color).build();

                // 이전에 있던 viewDOT.skillList에 add
                prevViewDTO.getSkillList().add(skillDTO);
            }else{
                // 스킬 리스트 생성
                List<SkillRequest.CompskillDTO> skillList = new ArrayList<>();

                String color = "";
                if (((String)job[7]).equals("jQuery")){
                    color = "badge bg-primary";
                }
                else if(((String)job[7]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)job[7]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)job[7]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)job[7]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)job[7]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)job[7]).equals("React")){
                    color = "badge bg-dark";
                }

                // 스킬 이름 set
                skillList.add(SkillRequest.CompskillDTO.builder().name((String) job[7]).color(color).build());

                // 새로운 DTO 생성
                prevViewDTO = new CompRequest.ResumeViewDTO();
                prevViewDTO.setId((Integer) job[0]);
                prevViewDTO.setUserId((Integer) job[1]);
                prevViewDTO.setMyName((String) job[2]);
                prevViewDTO.setTitle((String) job[3]);
                prevViewDTO.setEdu((String) job[4]);
                prevViewDTO.setCareer((String) job[5]);
                prevViewDTO.setArea((String) job[6]);
                prevViewDTO.setSkillList(skillList);
                viewDTOList.add(prevViewDTO);
            }

        }

        System.out.println(viewDTOList);

       // session.setAttribute("readResumeList", viewDTOList);


        return "/comp/scrap";
    }

    @GetMapping("/comp/talent")
    public String talent(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionComp");
        request.setAttribute("id", sessionUser.getId());

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

    @GetMapping("/comp/compResumeDetail/{id}")
    public String compResumeDetail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        System.out.println("/comp/compResumeDetail/{id} : " + id);
        Resume resumeDTO = resumeRepository.findById(id);
        request.setAttribute("resume", resumeDTO);

        if(sessionComp == null) {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id);
            request.setAttribute("scrap", scrapDetailDTO);
        } else {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id, sessionComp.getId());
            request.setAttribute("scrap", scrapDetailDTO);
        }




        return "/comp/compResumeDetail";
    }
}
