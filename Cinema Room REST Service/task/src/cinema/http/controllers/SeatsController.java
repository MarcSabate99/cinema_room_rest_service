package cinema.http.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

import static cinema.utils.RoomUtils.ROWS;
import static cinema.utils.RoomUtils.SEATS_PER_ROW;

@RestController
public class SeatsController {

    private final HashMap<String, Object> seatsInfo = new HashMap<>();

    @GetMapping("/seats")
    public HashMap<String, Object>  seats() {
        seatsInfo.put("total_rows", ROWS);
        seatsInfo.put("total_columns", SEATS_PER_ROW);
        seatsInfo.put("available_seats", new ArrayList<HashMap<String, Integer>>());
        ArrayList availableSeats = (ArrayList) seatsInfo.get("available_seats");
        for (int row = 1; row <= ROWS; row++) {
            for (int column = 1; column <= SEATS_PER_ROW; column++) {
                int price = row <= 4 ? 10: 8;
                HashMap<String, Integer> data = new HashMap<>();
                data.put("row", row);
                data.put("column", column);
                data.put("price", price);
                availableSeats.add(data);
            }
        }

        return seatsInfo;
    }

}
