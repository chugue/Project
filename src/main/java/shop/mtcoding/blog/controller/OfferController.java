package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.comp.CompRepository;
import shop.mtcoding.blog.model.jobs.JobResponse;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.offer.OfferRepository;
import shop.mtcoding.blog.model.offer.OfferRequest;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OfferController {
    private final JobsRepository jobsRepository;
    private final ApplyRepository applyRepository;
    private final OfferRepository offerRepository;
    private final HttpSession session;

    @GetMapping("/comp/{id}/apply")
    public String offer(@PathVariable Integer id, @RequestParam(required = false, defaultValue = "1") Integer jobsId, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        request.setAttribute("userId", sessionComp.getId());
        List<OfferRequest.CompOfterDTO> offerList = offerRepository.findAllByJobsId(jobsId);

        for (int i = 0; i < offerList.size(); i++) {
            OfferRequest.CompOfterDTO dto = offerList.get(i);
            dto.setId(i + 1);
            dto.setSkillList(applyRepository.findAllSkillById(dto.getId()));
        }

        request.setAttribute("offerList", offerList);

        List<JobResponse.JobListByUserId> jobList = jobsRepository.findAllByUserId(id);
        System.out.println(jobList);

        for (int i = 0; i < jobList.size(); i++) {
            JobResponse.JobListByUserId dto = jobList.get(i);
            dto.setId(i + 1);
            dto.setSkillList(jobsRepository.findAllSkillById(dto.getId()));
        }

        request.setAttribute("jobList", jobList);

        return "/comp/apply";
    }

    @PostMapping("")
    public void delete() {

    }
}
