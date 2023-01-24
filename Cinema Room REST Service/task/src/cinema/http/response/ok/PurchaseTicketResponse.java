package cinema.http.response.ok;

import java.util.HashMap;
import java.util.UUID;

public class PurchaseTicketResponse {
    public int row;
    public int column;
    public int price;
    public HashMap<String, Object> ticket;
    public PurchaseTicketResponse(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.ticket = new HashMap<>();
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public int getRow() {
        return row;
    }

    public HashMap<String, Object> getTicket() {
        ticket.put("row", this.row);
        ticket.put("column", this.column);
        ticket.put("price", this.price);

        return ticket;
    }

    public UUID getToken() {
        return UUID.randomUUID();
    }
}
