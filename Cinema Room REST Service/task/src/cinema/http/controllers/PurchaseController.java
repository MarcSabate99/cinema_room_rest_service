package cinema.http.controllers;

import cinema.http.entities.SeatPurchase;
import cinema.http.response.error.PurchaseRowColumnErrorResponse;
import cinema.http.response.error.TicketPurchasedErrorResponse;
import cinema.http.response.ok.PurchaseTicketResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

import static cinema.Main.inMemory;
import static cinema.utils.RoomUtils.ROWS;
import static cinema.utils.RoomUtils.SEATS_PER_ROW;

@RestController
public class PurchaseController {

    @PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity purchase(
            @RequestBody SeatPurchase seatPurchase
    ) {
        if(seatPurchase.getRow() > ROWS ||
           seatPurchase.getColumn() > SEATS_PER_ROW ||
           seatPurchase.getRow() < 0 ||
           seatPurchase.getColumn() < 0
        ) {
            PurchaseRowColumnErrorResponse purchaseRowColumnErrorResponse = new PurchaseRowColumnErrorResponse("The number of a row or a column is out of bounds!");
            HashMap<String,String> body = new HashMap<>();
            body.put("error", purchaseRowColumnErrorResponse.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        int[][] map = inMemory.getMap();
        if(map[seatPurchase.getRow()][seatPurchase.getColumn()] == 1) {
            TicketPurchasedErrorResponse ticketPurchasedErrorResponse = new TicketPurchasedErrorResponse("The ticket has been already purchased!");
            HashMap<String,String> body = new HashMap<>();
            body.put("error", ticketPurchasedErrorResponse.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        map[seatPurchase.getRow()][seatPurchase.getColumn()] = 1;
        PurchaseTicketResponse purchaseTicketResponse = new PurchaseTicketResponse(
                seatPurchase.getRow(),
                seatPurchase.getColumn(),
                seatPurchase.getPrice()
        );

        HashMap<String, String> tokens = inMemory.getTokens();
        UUID token = purchaseTicketResponse.getToken();
        tokens.put(seatPurchase.getRow() + "-" + seatPurchase.getColumn(), token.toString());
        HashMap<String, Object> body = new HashMap<>();
        body.put("ticket", purchaseTicketResponse.getTicket());
        body.put("token", token);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


}
