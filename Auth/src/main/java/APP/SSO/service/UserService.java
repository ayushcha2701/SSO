package APP.SSO.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import APP.SSO.dto.SignInRequest;
import APP.SSO.dto.SignInResponse;
import APP.SSO.entity.User;
import APP.SSO.exception.UserAlreadyExistsException;
import APP.SSO.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    private UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.encoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;

    }

    @Override
    public SignInResponse signIn(SignInRequest req) throws UserAlreadyExistsException {

        String emailId = req.getWorkEmailId();

        if (userRepository.findByWorkEmailId(emailId).isPresent()) {

            throw new UserAlreadyExistsException("User with this email id" + emailId + "already exist");

        }

        User user = new User();

        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setWorkEmailId(emailId);
        user.setPassword(encoder.encode(req.getPassword()));

        User saved = userRepository.save(user);

        return new SignInResponse(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getWorkEmailId());

    }

}
