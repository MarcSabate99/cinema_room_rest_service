package cinema.http.controllers;

import cinema.http.entities.Refund;
import cinema.http.response.error.TicketNotFoundErrorResponse;
import cinema.repository.InMemory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static cinema.Main.inMemory;


@RestController
public class RefundController {

    @PostMapping(value = "/return", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity refund(
            @RequestBody Refund refund
    ) {
        String token = refund.getToken();
        Integer[] data = inMemory.findToken(token);
        if(data == null) {
            TicketNotFoundErrorResponse ticketNotFoundErrorResponse = new TicketNotFoundErrorResponse("Wrong token!");
            HashMap<String,String> body = new HashMap<>();
            body.put("error", ticketNotFoundErrorResponse.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        inMemory.removeToken(data[0], data[1]);
        HashMap<String, Object> body = new HashMap<>();
        HashMap<String, Object> ticketInfo = new HashMap<>();
        ticketInfo.put("row", data[0]);
        ticketInfo.put("column", data[1]);
        ticketInfo.put("price", data[2]);
        body.put("returned_ticket", ticketInfo);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
