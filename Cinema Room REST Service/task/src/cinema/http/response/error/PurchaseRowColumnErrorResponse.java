package cinema.http.response.error;

public class PurchaseRowColumnErrorResponse {
    public String message;

    public PurchaseRowColumnErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
