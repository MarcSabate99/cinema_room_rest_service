package cinema.http.response.error;

public class TicketPurchasedErrorResponse {
    public String message;

    public TicketPurchasedErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
