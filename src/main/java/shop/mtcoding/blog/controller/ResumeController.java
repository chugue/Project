package shop.mtcoding.blog.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final HttpSession session;
    private final ResumeRepository resumeRepository;

    @GetMapping("/resume/manageResume")
    public String manageResume(HttpServletRequest request) {
        List<Resume> resumeList = resumeRepository.findAll();
        request.setAttribute("resumeList", resumeList);

        return "/resume/manageResume";
    }

    @GetMapping("/resume/resumeDetail")
    public String resumeDetail() {
        return "/resume/resumeDetail";
    }

    @GetMapping("/resume/updateResumeForm")
    public String updateResumeForm () {
        return "/resume/updateResumeForm";
    }


    @GetMapping("/resume/writeResumeForm")
    public String writeResumeForm() {

        return "/resume/writeResumeForm";
    }


    @PostMapping("/resume/save")
    public String save(ResumeRequest.ResumeWriterDTO requestDTO, HttpServletRequest request) {
        System.out.println(requestDTO);
        resumeRepository.save(requestDTO);

        return "redirect:/resume/manageResume";
    }

//
}

