package APP.SSO.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SignInResponse {
     
     private UUID id;
     private String firstName;
     private String lastName;
     private String workEmailId;

}
