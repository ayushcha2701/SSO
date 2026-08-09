package APP.SSO.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import APP.SSO.dto.LoginRequest;
import APP.SSO.dto.SignupRequest;
import APP.SSO.dto.SignupResponse;
import APP.SSO.entity.User;
import APP.SSO.exception.InvalidCredentialsException;
import APP.SSO.exception.UserAlreadyExistsException;
import APP.SSO.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder; 

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public SignupResponse signUp(SignupRequest req) throws UserAlreadyExistsException {

        String emailId = req.getWorkEmailId();

        if (userRepository.findByWorkEmailId(emailId).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + emailId + " already exists");
        }

        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setWorkEmailId(emailId);
        user.setPasswordHash(encoder.encode(req.getPassword()));

        User saved = userRepository.save(user);

        return new SignupResponse(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getWorkEmailId());
    }

    @Override
    public void login(LoginRequest req) throws InvalidCredentialsException {

        User user = userRepository.findByWorkEmailId(req.getWorkEmailId())
                .orElseThrow(InvalidCredentialsException::new);

        if (!encoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
    }
}