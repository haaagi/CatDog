package com.catdog.springboot.web;

import com.catdog.springboot.config.auth.LoginUser;
import com.catdog.springboot.config.auth.dto.SessionUser;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.service.PostsService;
import com.catdog.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.catdog.springboot.domain.user.User;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user)  {
        model.addAttribute("posts", postsService.findAllDesc());
        // 교재 ( User ) -> SessionUser
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }

    @GetMapping("/user/save")
    public String userSave() { return "user-save"; }

    @GetMapping("/test/save")
    public String testSave() { return "test-save"; }
}
