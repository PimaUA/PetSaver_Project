package ua.pima.petSaver.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.pima.petSaver.configuration.CustomUserDetailsService;
import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.dto.UserSearch;
import ua.pima.petSaver.entity.user.UserInfo;
import ua.pima.petSaver.service.UserService;

import java.security.Principal;
import java.util.List;
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
        Optional<UserInfo> optionalUserInfo = userService.findByUsername(signUpUserDto.getUsername());
        if (optionalUserInfo.isPresent() || emailExists(signUpUserDto.getEmail())) {
            model.addAttribute("userExists", optionalUserInfo);
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

   /* @GetMapping("/searchUser")
    public String searchUser(Model model,String value) {
        model.addAttribute("userSearch", new UserSearch(value));

        return "homeView";
    }*/

    @PostMapping("/searchUser")
    public String searchUser(@ModelAttribute UserSearch userSearch, Model model) {
        List<UserInfo> searchResults =
                userService.search(userSearch);
        model.addAttribute("searchResults", searchResults);
        return "homeView";
    }

    private boolean emailExists(String email) {
        return userService.findByEmail(email).isPresent();
    }
}
