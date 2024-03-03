package shop.mtcoding.blog.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.mtcoding.blog.model.jobs.JobRequest;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.user.User;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobsController {

    private final JobsRepository jobsRepository;
    private final HttpSession session;

    @GetMapping("/jobs/interest")
    public String interest () {
        return "/jobs/interest";
    }

    @GetMapping("/jobs/info")
    public String info () {
        return "/jobs/info";
    }

    @GetMapping("/jobs/jobsDetail")
    public String jobsDetail() {
        return "/jobs/jobsDetail";
    }
    @GetMapping("/jobs/updateJobsForm/{jobId}")
    public String updateJobsForm(@PathVariable Integer jobId){
        System.out.println(jobId);

        return "/jobs/updateJobsForm";
    }
    @PostMapping("/jobs/updateJob")
    public String updateJob(JobRequest.JobUpdateDTO jobUpdateDTO) {

        jobsRepository.update(jobUpdateDTO);

        return "redirect:/jobs/info";
    }

    @GetMapping("/jobs/writeJobsForm")
    public String writeJobsForm() {

        return "/jobs/writeJobsForm";
    }

    @PostMapping("/jobs/save")
    public String save(HttpServletRequest request , JobRequest.JobWriterDTO jobWriterDTO){
        jobsRepository.save(jobWriterDTO);
        return "redirect:/comp/comphome";
    }
}