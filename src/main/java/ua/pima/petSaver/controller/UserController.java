package ua.pima.petSaver.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.pima.petSaver.configuration.CustomUserDetailsService;
import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.entity.user.UserSecurityInfo;
import ua.pima.petSaver.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/home")
    public String showHomePage(Model model, Principal principal) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userDetails", userDetails);
        return "homeView";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model, SignUpUserDto signUpUserDto) {
        model.addAttribute("loginUser", signUpUserDto);
        return "loginView";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) { //,SignUpUserDto signUpUserDto
        //SignUpUserDto signUpUserDto = new SignUpUserDto();
        model.addAttribute("signupUser", new SignUpUserDto());
        return "registrationView";
    }

    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("signupUser") SignUpUserDto signUpUserDto
            , BindingResult bindingResult, Model model) {
        Optional<UserSecurityInfo> optionalUserSecurityInfo = userService.findByUsername(signUpUserDto.getUsername());
        if (optionalUserSecurityInfo.isPresent()) { //||
            model.addAttribute("userExists", optionalUserSecurityInfo);
            return "registrationView";
        }
        if (bindingResult.hasErrors()) {
            //model.addAttribute("signupUser", signUpUserDto);
            return "registrationView";
        }
        userService.save(signUpUserDto);
        return "redirect:/register?success";
    }

    @GetMapping("/allUsers")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsersView";
    }


}
