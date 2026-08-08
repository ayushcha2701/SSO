package APP.SSO.service;

import APP.SSO.dto.LoginRequest;
import APP.SSO.dto.SignupRequest;
import APP.SSO.dto.SignupResponse;
import APP.SSO.exception.UserAlreadyExistsException;
import APP.SSO.exception.InvalidCredentialsException;

public interface UserServiceInterface {

    SignupResponse signUp(SignupRequest req) throws UserAlreadyExistsException;

    void login(LoginRequest req) throws InvalidCredentialsException;
}