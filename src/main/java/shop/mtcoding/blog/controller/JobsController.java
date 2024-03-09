package shop.mtcoding.blog.controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.jobs.JobRequest;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobsController {
    private final JobsRepository jobsRepository;
    private final SkillRepository skillRepository;
    private final HttpSession session;

    @GetMapping("/jobs/interest")
    public String interest () {
        return "/jobs/interest";
    }

    @GetMapping("/jobs/info")
    public String info (HttpServletRequest request) {

        //List<Jobs> jobsList = jobsRepository.findAllV2();
        List<Object[]> jobsList = jobsRepository.findAllByUserId();
        List<JobRequest.JobsViewDTO> viewDTOList = new ArrayList<>();// 담는리스트
        JobRequest.JobsViewDTO prevViewDTO = new JobRequest.JobsViewDTO(); // 담는 로우하나

        for (int i = 0; i < jobsList.size(); i++) {

            Object[] job = jobsList.get(i);
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

                SkillRequest.JobsSkillDTO skillDTO = SkillRequest.JobsSkillDTO.builder().name((String) job[7]).color(color).build();

                // 이전에 있던 viewDOT.skillList에 add
                prevViewDTO.getSkillList().add(skillDTO);
            }else{
                // 스킬 리스트 생성
                List<SkillRequest.JobsSkillDTO> skillList = new ArrayList<>();

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
                skillList.add(SkillRequest.JobsSkillDTO.builder().name((String) job[7]).color(color).build());

                // 새로운 DTO 생성
                prevViewDTO = new JobRequest.JobsViewDTO();
                prevViewDTO.setId((Integer) job[0]);
                prevViewDTO.setUserId((Integer) job[1]);
                prevViewDTO.setTitle((String) job[2]);
                prevViewDTO.setEdu((String) job[3]);
                prevViewDTO.setCareer((String) job[4]);
                prevViewDTO.setArea((String) job[5]);
                prevViewDTO.setDeadLine((java.sql.Date) job[6]);
                prevViewDTO.setSkillList(skillList);
                viewDTOList.add(prevViewDTO);
            }

        }

        System.out.println(viewDTOList);

        session.setAttribute("jobsList", viewDTOList);

        return "/jobs/info";
    }


    @GetMapping("/jobs/jobsDetail/{id}")
    public String jobsDetail(@PathVariable Integer id, @RequestParam(defaultValue = "0") Integer page) {


        //List<>

        return "/jobs/jobsDetail";
    }

    @GetMapping("/jobs/{id}/updateJobsForm")
    public String updateJobsForm(@PathVariable Integer id){

        // 디비에서 아이디 row 들고오기
        Object[] job = jobsRepository.findById(id);
        List<String> skillNames = skillRepository.findALLNameByJobsId(id);

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
                .userId((Integer) job[11])
                .deadLine(String.valueOf((Date) job[12]))
                .skillChecked(new SkillResponse.SkillCheckedDTO(skillNames))
                .build();

        // row 세션에 담아
        session.setAttribute("job",jobDTO);

        System.out.println(skillNames);
        System.out.println(jobDTO);
        // 머스테치에 세션 데이터값 넣어주기

        return "/jobs/updateJobsForm";
    }

    @PostMapping("/jobs/{id}/update")
    public String updateJob(@PathVariable Integer id, JobRequest.JobUpdateDTO jobUpdateDTO) {

        jobsRepository.update(jobUpdateDTO,id);

        User compId = (User) session.getAttribute("sessionComp");

        // System.out.println(jobUpdateDTO.getCompId());
        return "redirect:/comp/"+ compId.getId() +"/comphome";
    }

    @GetMapping("/jobs/writeJobsForm")
    public String writeJobsForm() {

        return "/jobs/writeJobsForm";
    }

    @PostMapping("/jobs/save")
    public String save(JobRequest.JobWriterDTO jobWriterDTO){
        jobsRepository.save(jobWriterDTO);
//        session.setAttribute("jobList",jobWriterDTO);

        return "redirect:/comp/"+ jobWriterDTO.getUserId()+"/comphome";
    }

    @PostMapping("/jobs/{id}/delete")
    public String delete(HttpSession session,@PathVariable Integer id){

            User sessionUser = (User) session.getAttribute("sessionComp");
            Integer compId = sessionUser.getId();
            System.out.println(compId);

            jobsRepository.deleteById(compId,id);


            return "redirect:/comp/"+compId+"/comphome";
    }
}
