package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.model.skill.SkillRepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final HttpSession session;
    private final ResumeRepository resumeRepository;
    private final ScrapRepository scrapRepository;
    private final SkillRepository skillRepository;

    

    @GetMapping("/resume/{id}/manageResume")
    public String manageResume(@PathVariable Integer id, HttpServletRequest request) {
        List<Object[]> resumeList = resumeRepository.findAll(id);
        List<ResumeRequest.UserViewDTO> userViewDTOList = new ArrayList<>();
        ResumeRequest.UserViewDTO userViewDTO = new ResumeRequest.UserViewDTO();

        for (int i = 0; i < resumeList.size(); i++) {
            Object[] user = resumeList.get(i);
            if (userViewDTO.getId() == user[0]){
                // 스킬 스킬 색깔 설정

                String color = "";

                if (((String)user[7]).equals("jQuery")){
                    color = "badge bg-primary";
                }
                else if(((String)user[7]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)user[7]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)user[7]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)user[7]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)user[7]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)user[7]).equals("React")){
                    color = "badge bg-dark";
                }


                SkillRequest.UserskillDTO userskillDTO = SkillRequest.UserskillDTO.builder().name((String) user[7]).color(color).build();

                userViewDTO.getSkillList().add(userskillDTO);

            }else{

                List<SkillRequest.UserskillDTO> userskillDTO = new ArrayList<>();

                String color = "";

                if (((String)user[7]).equals("jQuery")){
                    color = "badge bg-primary";
                }
                else if(((String)user[7]).equals("javaScript")){
                    color = "badge bg-secondary";
                }
                else if(((String)user[7]).equals("Spring")){
                    color = "badge bg-success";
                }
                else if(((String)user[7]).equals("HTML/CSS")){
                    color = "badge bg-danger";
                }
                else if(((String)user[7]).equals("JSP")){
                    color = "badge bg-warning";
                }
                else if(((String)user[7]).equals("java")){
                    color = "badge bg-info";
                }
                else if(((String)user[7]).equals("React")){
                    color = "badge bg-dark";
                }


                userskillDTO.add(SkillRequest.UserskillDTO.builder().name((String) user[7]).color(color).build());

                userViewDTO = new ResumeRequest.UserViewDTO();

                userViewDTO.setId((Integer) user[0]);
                userViewDTO.setUserId((Integer) user[1]);
                userViewDTO.setTitle((String) user[2]);
                userViewDTO.setEdu((String) user[3]);
                userViewDTO.setArea((String) user[4]);
                userViewDTO.setResumeId((Integer) user[5]);
                userViewDTO.setCareer((String) user[6]);
                userViewDTO.setSkillList(userskillDTO);

                userViewDTO.getSkillList();



                userViewDTOList.add(userViewDTO);


            }

        }
        session.setAttribute("resumeUserList", userViewDTOList);
        userViewDTOList.get(0).getSkillList().get(0).getName();


        // return "/resume/manageResume";
        // User sessionUser = (User) session.getAttribute("sessionUser");
        return "/resume/manageResume";
//        return "redirect:/resume/" +sessionUser.getId() +"/manageResume";
    }



    @GetMapping("/resume/resumeDetail/{id}")
    public String resumeDetail (@PathVariable Integer id, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");


        Resume resumeDTO = resumeRepository.findById(id);
        request.setAttribute("resume", resumeDTO);


        if(sessionComp == null) {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id);
            request.setAttribute("scrap", scrapDetailDTO);
        } else {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id, sessionComp.getId());
            request.setAttribute("scrap", scrapDetailDTO);
        }
        return "/resume/resumeDetail";
    }

    // yz/0305 수정하기
    @GetMapping("/resume/{id}/updateResumeForm")
    public String updateResumeForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }

        Resume resumeDTO = resumeRepository.findById(id);
        request.setAttribute("resume", resumeDTO);

        return "/resume/updateResumeForm";
    }


    // yz/0305 수정하기
    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable int id, ResumeRequest.ResumeUpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        // 업데이트 메서드 실행
        resumeRepository.updateById(requestDTO, id);
        System.out.println("dsafesdafedsa : " + id);
        //return "/resume/"+id+"/updateResumeForm";
        return "redirect:/resume/" + sessionUser.getId() + "/manageResume";
    }

    @GetMapping("/resume/writeResumeForm")
    public String writeResumeForm() {

        return "/resume/writeResumeForm";
    }


    @PostMapping("/resume/save")
    public String save(ResumeRequest.ResumeWriterDTO requestDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        resumeRepository.save(requestDTO, sessionUser.getId());

        List<String> skills = requestDTO.getSkill();
        System.out.println();
//        for (int i = 0; i < skills.size(); i++) {
//
//        }

        return "redirect:/resume/" + sessionUser.getId() + "/manageResume";
    }

    // yz/0305 삭제하기
    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }
        //Resume resume = resumeRepository.findById(id);
        resumeRepository.deleteById(id);

        //request.setAttribute("resume", resumeDTO);

        return "redirect:/resume/" + sessionUser.getId() + "/manageResume";

    }


}