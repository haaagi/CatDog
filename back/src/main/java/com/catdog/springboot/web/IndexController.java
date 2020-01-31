package com.catdog.springboot.web;

import com.catdog.springboot.config.auth.LoginUser;
import com.catdog.springboot.config.auth.dto.SessionUser;
import com.catdog.springboot.service.PostsService;
import com.catdog.springboot.web.dto.PostsListResponseDto;
import com.catdog.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {
    public static HashMap<Long, Long> map;
    private final PostsService postsService;
    private final HttpSession httpSession;

    public IndexController(PostsService postsService, HttpSession httpSession) {
        this.postsService = postsService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user)  {
        map = new HashMap<>();
        List<PostsListResponseDto> posts = postsService.findAllAsc();
        List<Object[]> likescount = postsService.likescount();

        if(posts.size() > 0) {
            for(int i=0; i<likescount.size(); i++){
                String key = String.valueOf(likescount.get(i)[0]);
                String value = String.valueOf(likescount.get(i)[1]);
                map.put(Long.valueOf(key) , Long.valueOf(value));
            }
            for(int i=0; i<posts.size(); i++){
                if(map.containsKey(posts.get(i).getPid())) posts.get(i).setLikes(map.get(posts.get(i).getPid()));
                else posts.get(i).setLikes(0L);
            }

            model.addAttribute("posts", posts);

        }else {
            model.addAttribute("posts", posts);
        }
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        //model.addAttribute("userEmail", user.getEmail());
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
