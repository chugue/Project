package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.apply.ApplyRequest;
import shop.mtcoding.blog.model.user.User;

@Controller
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyRepository applyRepository;
    private final HttpSession session;

    @PostMapping("/jobs/apply/save")
    public String applySave(ApplyRequest.saveDTO requestDTO) {

        applyRepository.save(requestDTO);
        return "redirect:/jobs/jobsDetail/" + requestDTO.getJobsId();
    }

    @PostMapping("/apply/pass/update/{id}")
    public String applyPassUpDate(@PathVariable Integer id, @RequestParam("jobsId")Integer jobsId) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        applyRepository.passUpdate(id, jobsId);
        return "redirect:/comp/" + sessionComp.getId() + "/comphome?jobsId=" + jobsId;
    }

    @PostMapping("/apply/fail/update/{id}")
    public String applyFailUpDate(@PathVariable Integer id, @RequestParam("jobsId")Integer jobsId) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        applyRepository.failUpdate(id, jobsId);
        return "redirect:/comp/" + sessionComp.getId() + "/comphome?jobsId=" + jobsId;
    }

    @PostMapping("/apply/pass2/update/{id}")
    public String applyPassUpDate2(@PathVariable Integer id, @RequestParam("jobsId") Integer jobsId) {
        applyRepository.passUpdate(id, jobsId);
        return "redirect:/resume/resumeDetail/" + id + "?jobsId=" + jobsId;
    }

    @PostMapping("/apply/fail2/update/{id}")
    public String applyFailUpDate2(@PathVariable Integer id, @RequestParam("jobsId") Integer jobsId) {
        applyRepository.failUpdate(id, jobsId);
        return "redirect:/resume/resumeDetail/" + id + "?jobsId=" + jobsId;
    }
}
