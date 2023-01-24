package cinema.http.response.error;

public class PasswordErrorResponse {

    public String message;

    public PasswordErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
