package shop.mtcoding.blog.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.model.jobs.JobRequest;
import shop.mtcoding.blog.model.jobs.JobsRepository;

import java.util.Date;

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

        //List<BoardResponse.ReplyDTO> replyDTOList = replyRepository.findById(id);


        //request.setAttribute("isScrap", true);




        return "/jobs/jobsDetail";
    }
    @GetMapping("/jobs/updateJobsForm/{jobId}")
    public String updateJobsForm(@PathVariable Integer jobId){

        // 디비에서 아이디 row 들고오기
        Object[] job = jobsRepository.findById(jobId);

        for(Object o : job){
            System.out.println(o);
        }

        JobRequest.JobJoinDTO jobDTO = JobRequest.JobJoinDTO.builder()
                .compName((String) job[0])
                .businessNumber((String) job[1])
                .phone((String) job[2])
                .area((String) job[3])
                .edu((String) job[4])
                .career((String) job[5])
                .content((String) job[6])
                .title((String) job[7])
                .id((Integer) job[8])
                .homepage((String) job[9])
                .task((String) job[10])
                .deadLine(String.valueOf((Date) job[11]))
                .build();


        // row 세션에 담아
        session.setAttribute("job",jobDTO);
        // 머스테치에 세션 데이터값 넣어주기

        return "/jobs/updateJobsForm";
    }
    @PostMapping("/jobs/updateJob")
    public String updateJob(JobRequest.JobUpdateDTO jobUpdateDTO) {
        System.out.println(jobUpdateDTO);
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
        session.setAttribute("jobList",jobWriterDTO);
        return "redirect:/comp/comphome/" + jobWriterDTO.getCompId();
    }
}
