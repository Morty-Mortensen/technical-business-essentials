package com.essentials.business.technical.controller;

import com.essentials.business.technical.dao.selenium.SeleniumFortuneFiveHundredDao;
import com.essentials.business.technical.model.request.FortuneFivehundredCompanyYearsRequest;
import com.essentials.business.technical.service.FortuneFiveHundredService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
public class FortuneFivehundredController {

    @PostMapping("fortunefivehundred/companies")
    public Map<String, String> getCompanies(@RequestBody FortuneFivehundredCompanyYearsRequest fortuneCompanyYears) {
        FortuneFiveHundredService service = getFortuneFivehundredService();
        return service.getCompaniesUrls(fortuneCompanyYears.getYears());
    }

    private FortuneFiveHundredService getFortuneFivehundredService() {
        return new FortuneFiveHundredService(new SeleniumFortuneFiveHundredDao());
    }

}

//    @RequestMapping("fortunefivehundred/companies")
//    public Map<String, String> handleRequest(HttpServletRequest req, HttpServletResponse resp) {
//        HttpSession session = req.getSession();
//        String years = req.getParameter("years");
//
//        Map<String, String> companies = new HashMap<>();
//        companies.put("2021", "https://google.com");
//        session.setAttribute("years", years);
//
//        return companies;
//    }

// can send values (?name=Tyler&year=2021) and receive as object (Person person)
//    @RequestMapping("fortunefivehundred/companies/{year}")
//    @GetMapping("fortunefivehundred/companies/{year}")
//    public Map<String, String> getCompanies(@PathVariable("year") String year, @RequestParam("name") String myYears, HttpSession session) {
//
//        Map<String, String> companies = new HashMap<>();
//        companies.put("2021", "https://google.com");
//        session.setAttribute("years", myYears);
//
//        return companies;
//    }
//
//    @PostMapping("something")
//    public void postSomething(@RequestBody User user) {
//        System.out.println(user.getFirstName());
//    }

//@RepositoryRestResource(collectionResourceRel = "aliens", path = "aliens")
//public interface UserRepo extends CrudRepository<User, Integer> {
//    List<User> getByName(String name);
//
//    List<User> getByIdGreaterThan(int id);
//
//    @Query("SELECT * FROM User WHERE id=?1 ORDER BY name")
//    List<User> ownQuery(int id);
//}

// Is there something similar for SQL as there is JpaRepository
