package APP.SSO.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Serves the HTML pages.
 *
 * Each method returns a view name — a plain string with no folder and no
 * extension. Thymeleaf turns "index" into src/main/resources/templates/index.html.
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String landing() {
        return "index";
    }
}
