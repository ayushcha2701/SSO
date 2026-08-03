package APP.SSO.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Serves the HTML pages.
 *
 * Each method returns a view name — a plain string with no folder and no
 * extension. Thymeleaf turns "index" into src/main/resources/templates/index.html.
 */
@Controller
@RequestMapping("/acmeConsole")
public class PageController {

    @GetMapping("/signUp")
    public String landing() {
        return "index";
    }

    @GetMapping("/signIn")
    public String signIn(){
        return "sso-login";
    }

    @GetMapping("/createAccount")
    public String createAccount(){
        return "createAcct";
    }
}
