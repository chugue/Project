package shop.mtcoding.blog.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.blog.dto.user.UserRequest;
import shop.mtcoding.blog.model.profile.ProfileRepository;
import shop.mtcoding.blog.model.profile.ProfileRequest;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRepository;
import shop.mtcoding.blog.util.ApiUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final HttpSession session;

    //아이디 중복체크 용. 이메일로 회원가입해서 email을 해줌
    @GetMapping("/api/user/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ApiUtil<>(true);
        } else {
            return new ApiUtil<>(false);
        }

    }


    @PostMapping("/user/join/{role}")
    public String join(@PathVariable int role, UserRequest.UserAllDTO requestDTO) {
        requestDTO.setRole(role);
        userRepository.save(requestDTO);
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
        return "redirect:/";
    }

    @PostMapping("/user/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        User user = (User) userRepository.findByEmailAndPassword(requestDTO);
        int role;
        if (user == null) {
            return "errors/401";
        } else {
            role = user.getRole();
            if (role == 1) {
                session.setAttribute("sessionUser", user);
                return "redirect:/";
            } else if (role == 2) {
                session.setAttribute("sessionComp", user);
                return  "redirect:/comp/jobsInfo";
            }
        }
        return "user/loginForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/user/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/user/offer")
    public String offer() {
        return "/user/offer";
    }


    @GetMapping("/user/scrap")
    public String scrap() {
        return "/user/scrap";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "/user/updateForm";
    }

    @GetMapping("/user/userHome")
    public String userHome() {
        return "/user/userHome";
    }

    // 이미지업로드용
    @PostMapping("/user/profileUpload")
    public String profileUpload(ProfileRequest.ProfileDTO requestDTO) {
        // 1. 데이터 전달 받기
        MultipartFile imgFile = requestDTO.getPhoto();
        String imgFileName = imgFile.getOriginalFilename();

        // 2. 파일저장 위치 설정해서 파일을 저장 (UUID 붙여서 롤링)
        // 랜덤한 UUID값에 _붙이고 뒤에 오리지널 파일 이름 붙여줌 그러고 저장소에 저장 (롤링)
        // String imgFilename = UUID.randomUUID() + "_" +imgFile.getOriginalFilename();

        // 저장소에 롤링한 이름의 이미지를 업로드
        // Path imgPath = Paths.get("./upload/" + imgFile);
        Path imgPath = Paths.get("./src/main/resources/photos/" + imgFileName);

        try {
            Files.write(imgPath, imgFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/user/profileUpdateForm";
    }

    // 이미지업로드용
    @GetMapping("/user/profileUpdateForm")
    public String profileUpdateForm(HttpServletRequest request) {

        // User user = profileRepository.findById(여기다 유저 키값 DTO로 받아오면 됨);

        // request.setAttribute("image", user);

        return "/user/profileUpdateForm";
    }
}
