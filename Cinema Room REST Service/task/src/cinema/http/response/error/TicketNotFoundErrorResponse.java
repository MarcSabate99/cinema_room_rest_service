package cinema.http.response.error;

public class TicketNotFoundErrorResponse {
    public String message;

    public TicketNotFoundErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
