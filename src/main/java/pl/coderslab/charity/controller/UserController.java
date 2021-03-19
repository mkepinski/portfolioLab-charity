package pl.coderslab.charity.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.email.EmailServiceImpl;
import pl.coderslab.charity.model.OnRegistrationCompleteEvent;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.token.VerificationToken;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;
    private final ApplicationEventPublisher eventPublisher;

    public UserController(UserRepository userRepository, EmailServiceImpl emailService, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               @ModelAttribute User user,
                               Model model) {

        User userToLogin = userRepository.findUserByEmail(email);
        if(userToLogin == null || !user.getPassword().equals(userToLogin.getPassword())) {
            model.addAttribute("user", user);
            return "login";
        }
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String processRegister(@Valid User user, BindingResult result,
//                                  HttpSession session) {
//        if(result.hasErrors()) {
//            return "register";
//        }
//        String givenPassword = user.getPassword();
//        String hashedPassword = BCrypt.
//        userRepository.save(user);
//        emailService.sendSimpleMessage(user.getEmail(), "Account activation", "none");
//
//        return "redirect:/";
//    }

    @PostMapping("/register")
    public ModelAndView registerAccount(@Valid User user,
                                        HttpServletRequest request,
                                        Errors errors) {
        try {
            User registered = userRepository.save(user);

            String appurl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appurl));
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("register", "user", user);
            mav.addObject("message", "Konto o podanym adresie email lub loginie już istnieje.");
            return mav;
        } catch(RuntimeException ex) {
            return new ModelAndView("email_error", "user", user);
        }

        return new ModelAndView("register_success", "user", user);
    }

    @GetMapping("/regitrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale)
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        service.saveRegisteredUser(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }
}
