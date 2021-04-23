package com.upgrad.phonedirectory.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.upgrad.phonedirectory.model.User;
import com.upgrad.phonedirectory.repository.UserRepository;
import com.upgrad.phonedirectory.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /*Index*/
    @RequestMapping("/")
    private String index(){
        return "index";
    }

    /*Signup*/
    /*GET*/
    @RequestMapping("/signup")
    private String signup(){
        return "signup";
    }

    /*POST*/
    @RequestMapping(method = RequestMethod.POST,value = "/signup")
    private String createacc(
            User user,
            Model model,
            HttpSession session
    )throws Exception{
        if(userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameExists", true);

            return "signup";
        }

        if(userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailExists", true);

            return "signup";
        }
        if(userService.findByPhoneNumber(user.getPhoneNumber()) != null) {
            model.addAttribute("phoneNumberExists", true);

            return "signup";
        }
        if(userService.findByPassword(user.getPassword()) != null) {
            model.addAttribute("passwordExists", true);

            return "signup";
        }
        userService.createUser(user);
        session.setAttribute("LoggedUser", user);
        return "redirect:/dash";
    }

    /*LOGIN*/
    /*GET*/
    @RequestMapping("/login")
    private String login(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/login")
    private String loginValid(
            Model model,
            User user,
            HttpSession session
    )throws Exception {

        if (userService.findByUsername(user.getUsername()) == null) {

            model.addAttribute("usernameInvalid", true);
            return "login";
        }
        User currentUser = userService.findByUsername(user.getUsername());

        if (!currentUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("passwordInvalid", true);
            return "login";
        }
        	session.setAttribute("LoggedUser", currentUser);
            return "redirect:/dash";


    }

}
