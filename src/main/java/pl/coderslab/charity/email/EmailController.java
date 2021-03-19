package pl.coderslab.charity.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    private final EmailServiceImpl emailService;

    public EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/msg")
    @ResponseBody
    public String mailSender() {
        emailService.sendSimpleMessage("jczarnecki4k2@gmail.com", "testSubject", "message");
        return "message sent.";
    }
}
