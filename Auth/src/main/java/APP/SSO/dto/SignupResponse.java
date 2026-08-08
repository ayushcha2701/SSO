package APP.SSO.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String workEmailId;
}