package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.resume.ResumeRepository;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyRepository applyRepository;
    private final JobsRepository jobsRepository;
    private final ResumeRepository resumeRepository;


    @PostMapping("/jobs/jobsDetail/{jobsId}/apply")
    public String apply (@RequestParam("resume_id") Integer resumeId, @PathVariable Integer jobsId, HttpServletRequest request){
        applyRepository.saveResumeJobsApply(resumeId, jobsId);
        applyRepository.findByResumeJobs(resumeId,jobsId);

        return "/jobs/jobsDetail";
    }
}
