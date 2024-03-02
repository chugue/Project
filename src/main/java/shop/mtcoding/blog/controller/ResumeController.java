package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {

    @GetMapping("/resume/manageResume")
    public String manageResume () {
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
