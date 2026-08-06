package APP.SSO.service;

import APP.SSO.dto.SignInRequest;
import APP.SSO.dto.SignInResponse;
import APP.SSO.exception.UserAlreadyExistsException;
import APP.SSO.exception.UserDoesNotExistsException;
import APP.SSO.exception.WrongPasswordException;

public interface UserServiceInterface {
    
      public SignInResponse signIn(SignInRequest req) throws UserAlreadyExistsException;
      public String login(String email, String password ) throws UserDoesNotExistsException, WrongPasswordException;
}
