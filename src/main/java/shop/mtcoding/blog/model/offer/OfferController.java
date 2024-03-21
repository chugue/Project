package shop.mtcoding.blog.model.offer;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.jobs.JobsRepository;

@Controller
@RequiredArgsConstructor
public class OfferController {
    private final JobsRepository jobsRepository;
    private final ApplyRepository applyRepository;
    private final OfferRepository offerRepository;
    private final HttpSession session;

    @PostMapping("/resume/offer/save")
    public String save(OfferRequest.SaveDTO saveDTO) {
        offerRepository.save(saveDTO, 1);

        return "redirect:/resume/resumeDetail/" + saveDTO.getResumeId();
    }

    @PostMapping("/resume/offer/{id}/delete")
    public String delete1(@PathVariable Integer id, OfferRequest.DeleteDTO DeleteDTO) {
        offerRepository.deleteById(session.getAttribute("jobsId2"), DeleteDTO);
        return "redirect:/resume/resumeDetail/" + DeleteDTO.getResumeId();
    }
}