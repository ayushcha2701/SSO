package APP.SSO.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInResponse {
     
     private UUID id;
     private String firstName;
     private String lastName;
     private String workEmailId;

}
