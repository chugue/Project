package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final HttpSession session;
    private final ResumeRepository resumeRepository;
    private final ScrapRepository scrapRepository;


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

    // 수정관리 페이지 ----------

    @GetMapping("/resume/{id}/manageResume")
    public String manageResume(@PathVariable Integer id,HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeRequest.UserViewDTO> resumeList = resumeRepository.findAllUserId(id);
        System.out.println(request); // 이거 스킬 안넣었을때 리스트

        for (int i = 0; i < resumeList.size(); i++) {
            //우리가 아까만든 생성자에 resumeList 값들이 들어간다
            ResumeRequest.UserViewDTO dto = resumeList.get(i);
            dto.setSkillList(resumeRepository.findAllByResumeId(dto.getId()));
        }

        request.setAttribute("resumeList", resumeList);
        System.out.println(request); // 이건 스킬추카하고 나서 리스트
//        List<SkillResponse.ResumeSkillDTO> resumeSkillList = resumeRepository.findAllByResumeId(id);

        return "/resume/manageResume";
    }

    @GetMapping("/resume/{id}/writeResumeForm")
    public String writeResumeForm() {
        User user = (User) session.getAttribute("sessionUser");

        System.out.println(user);
        return "/resume/writeResumeForm";
    }

    // 업데이트 -------------

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


    // 수정 업데이트 지우지 마세요
    // yz/0305 수정하기
    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable int id, ResumeRequest.ResumeUpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        // 업데이트 메서드 실행
        resumeRepository.updateById(requestDTO, id);

        return "redirect:/resume/" + sessionUser.getId() + "/manageResume";
    }

    // 글쓰기 --------------
    @PostMapping("/resume/save")
    public String save(ResumeRequest.ResumeWriterDTO requestDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeRepository.save(requestDTO);


        return "redirect:/resume/"+ requestDTO.getUserId() +"/manageResume";
    }


    // yz/0305 삭제하기
    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }
        //Resume resume = resumeRepository.findById(id);
        resumeRepository.deleteById(id);

        //request.setAttribute("resume", resumeDTO);

        return "redirect:/resume/"+ sessionUser.getId() +"/manageResume";

    }

}