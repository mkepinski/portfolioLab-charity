package pl.coderslab.charity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RandomController {


    @RequestMapping("/register")
    public String showSth() {
        return "form-confirmation";
    }
}
