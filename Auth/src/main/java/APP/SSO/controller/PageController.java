package APP.SSO.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import APP.SSO.dto.SignInRequest;
import APP.SSO.dto.SignInResponse;
import APP.SSO.exception.UserAlreadyExistsException;
import APP.SSO.service.UserServiceInterface;
import jakarta.validation.Valid;

/**
 * Serves the HTML pages.
 *
 * Each method returns a view name — a plain string with no folder and no
 * extension. Thymeleaf turns "index" into
 * src/main/resources/templates/index.html
 */
@Controller
@RequestMapping("/acmeConsole")
public class PageController {

    private final UserServiceInterface userServiceInterface;

    public PageController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

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

    @PostMapping("/saveDetails")
    @ResponseBody
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest req)
            throws UserAlreadyExistsException {
        SignInResponse res = userServiceInterface.signIn(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
