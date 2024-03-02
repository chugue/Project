package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final ResumeRepository resumeRepository;

    @GetMapping("/resume/manageResume")
    public String manageResume (HttpServletRequest request) {
        List<Resume> resumeList = resumeRepository.findAll();
        request.setAttribute("resumeList", resumeList);

        return "/resume/manageResume";
    }

    @GetMapping("/resume/resumeDetail")
    public String resumeDetail () {
        return "/resume/resumeDetail";
    }

    @GetMapping("/resume/updateResumeForm")
    public String updateResumeForm () {


        return "/resume/updateResumeForm";
    }
    @GetMapping("/resume/writeResumeForm")
    public String writeResumeForm () {
        return "/resume/writeResumeForm";
    }


}
