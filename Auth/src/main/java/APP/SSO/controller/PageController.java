package APP.SSO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Serves the HTML pages.
 *
 * Each method returns a view name — a plain string with no folder and no
 * extension. Thymeleaf turns "index" into
 * src/main/resources/templates/index.html
 *
 * Pages only. The JSON API lives in AuthController.
 */
@Controller
@RequestMapping("/acmeConsole")
public class PageController {

    @GetMapping("/")
    public String landing() {
        return "index";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "sso-login";
    }

    @GetMapping("/createAccount")
    public String createAccount() {
        return "createAcct";
    }
}