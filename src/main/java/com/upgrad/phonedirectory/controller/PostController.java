package com.upgrad.phonedirectory.controller;


import com.upgrad.phonedirectory.model.Post;
import com.upgrad.phonedirectory.model.User;
import com.upgrad.phonedirectory.service.PostService;
import com.upgrad.phonedirectory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @RequestMapping("/dash")
    private String dash(
            Model model,
            HttpSession session
    ){
        User user = (User) session.getAttribute("LoggedUser");
        List<Post> posts= user.getPosts();
        return "dash";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/dash")
    private String post(
            Post newPost,
            Model model,
            HttpSession session
            ){
        User user = (User) session.getAttribute("LoggedUser");
        newPost.setUser(user);
        postService.addContact(newPost);
        return "dash";
    }
}
