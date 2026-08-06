package APP.SSO.service;

import APP.SSO.dto.SignInRequest;
import APP.SSO.dto.SignInResponse;
import APP.SSO.exception.UserAlreadyExistsException;

public interface UserServiceInterface {
    
      public SignInResponse signIn(SignInRequest req) throws UserAlreadyExistsException;
      public String login();
}
