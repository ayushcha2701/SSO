package APP.SSO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import APP.SSO.dto.LoginRequest;
import APP.SSO.dto.LoginResponse;
import APP.SSO.dto.RequestStatus;
import APP.SSO.dto.SignupRequest;
import APP.SSO.dto.SignupResponse;
import APP.SSO.exception.InvalidCredentialsException;
import APP.SSO.exception.UserAlreadyExistsException;
import APP.SSO.service.UserServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * JSON API for authentication.
 *
 * Separate from PageController: that one serves HTML pages, this one serves
 * data. Mixing the two in a single class means every method needs its own
 * @ResponseBody and the class has two unrelated jobs.
 *
 * Exceptions are not caught here — GlobalExceptionHandler turns them into
 * HTTP responses so every endpoint fails consistently.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserServiceInterface userService;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public AuthController(UserServiceInterface userService) {
        this.userService = userService;
        
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signUp(@Valid @RequestBody SignupRequest req)
            throws UserAlreadyExistsException {

        return new ResponseEntity<>(userService.signUp(req), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req, HttpServletRequest request, HttpServletResponse response)
            throws InvalidCredentialsException {

        userService.login(req);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( req.getWorkEmailId(), null, List.of());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        return ResponseEntity.ok(new LoginResponse(RequestStatus.SUCCESS));
    }
}