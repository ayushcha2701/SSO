package APP.SSO.exception;

/**
 * Thrown when a login fails — whether the account does not exist or the
 * password is wrong.
 *
 * Deliberately one exception for both cases. Telling a caller "that email is
 * not registered" hands an attacker a free directory of your users, so the
 * response must be identical either way (security rule #4).
 */
public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException() {
        super("Invalid username or password");
    }
}