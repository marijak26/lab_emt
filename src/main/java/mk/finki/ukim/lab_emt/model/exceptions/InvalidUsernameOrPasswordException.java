package mk.finki.ukim.lab_emt.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException(){
        super("Invalid username or password");
    }
}
