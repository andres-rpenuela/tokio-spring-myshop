package com.tokioschool.myshop.controller;

import com.tokioschool.myshop.domain.User;
import com.tokioschool.myshop.dto.UserFormDto;
import com.tokioschool.myshop.exception.UserRegistrationException;
import com.tokioschool.myshop.service.FileService;
import com.tokioschool.myshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;
import java.util.Optional;


/**
 * Controlador para la gestión de usuarios
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registerUser(Model model) {
        model.addAttribute("user", new UserFormDto());
        return "registration";
    }

    @GetMapping({"/edit-user","/edit-user/{idUser}"})
    public ModelAndView editUser(@PathVariable(name="idUser", required = false) Long idUser, Model model) {
        final ModelAndView modelAndView = new ModelAndView("registration");
        final UserFormDto maybeUserFormDto = userService.findByUserId(idUser)
                .orElseGet(UserFormDto::new);
        if(!model.containsAttribute("user")){
            model.addAttribute("user", maybeUserFormDto);
        }
        modelAndView.addAllObjects(model.asMap());
        return  modelAndView;
    }


    @PostMapping("/new-user")
    public RedirectView addUser(@Valid @ModelAttribute("user") UserFormDto userFormDto, BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) throws UserRegistrationException {
        logger.info("inicio addUser");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(model.asMap());

        if(bindingResult.hasErrors()){
            if(!model.containsAttribute("user")){
                model.addAttribute("user", userFormDto);
            }
            modelAndView.addAllObjects(model.asMap());
            modelAndView.getModel().forEach(redirectAttributes::addFlashAttribute);
            final String urlTarget = Optional.ofNullable(userFormDto.getId())
                    .map("/edit-user/%d"::formatted)
                    .orElseGet(()->"/edit-user");

            return  new RedirectView(urlTarget);
        }

        boolean userAdded = userService.add(userFormDto);
        if (!userAdded)
            throw new UserRegistrationException("Error al registrar el usuario");

        logger.info("Usuario creado: " + userFormDto);
        model.addAttribute("user", userFormDto);
        logger.info("final addUser");

        redirectAttributes.addAttribute("user",userFormDto);
        return new RedirectView("/new-user");
    }

    @GetMapping("/new-user")
    public String newUser(Model model) throws UserRegistrationException {
        Optional.ofNullable(model.getAttribute("user"))
                .map(User.class::cast)
                .orElseThrow(()->new UserRegistrationException("No se ha registrado ningun usuario."));
        return "new-user";
    }
    @ExceptionHandler(UserRegistrationException.class)
    public ModelAndView handleUserRegistrationException(HttpServletRequest request, UserRegistrationException exception) {
        logger.error("Error: " + exception.getMessage(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "No se ha podido registrar el usuario. Por favor contacte con soporte técnico");
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler
    public ModelAndView handleException(HttpServletRequest request, Exception exception) {
        logger.error("Error: " + exception.getMessage(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}