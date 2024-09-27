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
import ua.pima.petSaver.entity.UserSecurityInfo;
import ua.pima.petSaver.service.UserService;

import java.security.Principal;

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
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model, SignUpUserDto signUpUserDto) {
        model.addAttribute("signUpUser", signUpUserDto);
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model, SignUpUserDto signUpUserDto) {
        //SignUpUserDto signUpUserDto = new SignUpUserDto();
        model.addAttribute("signUpUser", signUpUserDto);
        return "registration";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("signUpUser") @Valid SignUpUserDto signUpUserDto
            , Model model, BindingResult bindingResult) {
        UserSecurityInfo userSecurityInfo = userService.findByUsername(signUpUserDto.getUsername());
        if(userSecurityInfo != null){
            model.addAttribute("userExits", userSecurityInfo);
            bindingResult.rejectValue("username",
                    "There is already an account registered with the same name");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.saveUserSecurityInfo(signUpUserDto);
        return "redirect:/register?success";
    }
}
