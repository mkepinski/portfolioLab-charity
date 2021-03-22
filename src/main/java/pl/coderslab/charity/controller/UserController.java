package pl.coderslab.charity.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.email.EmailSenderService;
import pl.coderslab.charity.model.ConfirmationToken;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.ConfirmationTokenRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final EmailSenderService emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;

    public UserController(UserRepository userRepository, EmailSenderService emailService, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
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
                               Model model,
                               HttpSession session) {

        User userToLogin = userRepository.findByEmailIgnoreCase(email);
        if(userToLogin == null || !user.getPassword().equals(userToLogin.getPassword())) {
            model.addAttribute("user", user);
            return "login";
        }
        if(user.isEnabled()) {
            session.setAttribute("user", user);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView) {

        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user) {

        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());

        if(existingUser != null) {
            modelAndView.addObject("message", "Podany adres email już istnieje!");
            modelAndView.setViewName("error");
        } else {
            userRepository.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Rejestracja ukończona");
            mailMessage.setFrom("kepinski.poznan@gmail.com");
            mailMessage.setText("Aby aktywować konto, kliknij w link: " +
                                "http://localhost:8080/user/confirm-account?token=" +
                                confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.setViewName("register_success");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {

            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("registered");

        } else {

            modelAndView.addObject("message", "Nieprawidłowy link");
            modelAndView.setViewName("error");

        }

        return modelAndView;
    }

//    @RequestMapping(value = "/passwordReset", method = RequestMethod.GET)
//    public ModelAndView displayPasswordReset(ModelAndView modelAndView) {
//        return
//    }
}
