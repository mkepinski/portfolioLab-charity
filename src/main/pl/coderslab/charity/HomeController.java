package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

//    private final InstitutionRepository institutionRepository;
//    private final DonationRepository donationRepository;
//
//    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
//        this.institutionRepository = institutionRepository;
//        this.donationRepository = donationRepository;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeAction(Model model) {

//        List<Institution> institutionList = institutionRepository.findAll();
//        model.addAttribute("allInstitutions", institutionList);
//        model.addAttribute("bags", donationRepository.numberOfBags());
//        model.addAttribute("donations", donationRepository.numberOfDonations());

        return "index";
    }
}
