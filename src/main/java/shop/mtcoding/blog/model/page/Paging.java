package shop.mtcoding.blog.model.page;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRepository;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Data
@Component
public class Paging {
    private final JobsRepository jobsRepository;
    final int SHOW_PAGES = 9; //화면에 표현하고 싶은 게시물개수 (이력서 or 공고)
    int currentPage;
    int prevPage;
    int nextPage;

    public boolean firstPage(int page) {
        boolean firstPage;
        currentPage = page;
        prevPage = currentPage - 1;
        firstPage = prevPage == 0;
        return firstPage;
    }

    public boolean lastPage(int page) {
        boolean lastPage;
        currentPage = page - 1;
        int totalPosts = jobsRepository.findAll().size();
        lastPage = (totalPosts - (SHOW_PAGES * currentPage)) < SHOW_PAGES;
        return lastPage;
    }

    public List<Jobs> showPages(int page) {
        currentPage = page;
        List<Jobs> pageList = jobsRepository.findAll();
        ArrayList<Jobs> jobsList = new ArrayList<>();
        int totalPosts = pageList.size();
        int start = (SHOW_PAGES * currentPage) - SHOW_PAGES;
        int end = SHOW_PAGES * currentPage;

        // 현재 페이지에 필요한 공고만 조회해서 출력
        for (int j = start; j < end; j++) {
            if (j >= totalPosts) {
                break;
            }
            jobsList.add(pageList.get(j));
        }
        return jobsList;
    }

    public List<Jobs> showPages(int page, String keyword) {
        currentPage = page;
        List<Jobs> pageList = jobsRepository.findAll(keyword);
        ArrayList<Jobs> jobsList = new ArrayList<>();
        int totalPosts = pageList.size();
        int start = (SHOW_PAGES * currentPage) - SHOW_PAGES;
        int end = SHOW_PAGES * currentPage;

        for (int j = start; j < end; j++) {
            if (j >= totalPosts) {
                break;
            }
            jobsList.add(pageList.get(j));
        }
        return jobsList;
    }

    public int totalPages() {
        List<Jobs> pageList = jobsRepository.findAll();
        int totalPosts = pageList.size();
        int remainder = totalPosts % SHOW_PAGES;
        int division = totalPosts / SHOW_PAGES;
        int totalPages = (remainder <= SHOW_PAGES) ? division + 1 : division;

        return totalPages;
    }

    public int currentPage(int postId) {
        List<Jobs> pageList = jobsRepository.findAll();
        int totalPosts = pageList.size();
        int currentPage =((totalPosts - postId)/SHOW_PAGES) + 1;
        return currentPage;
    }
}
