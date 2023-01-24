package cinema.http.controllers;

import cinema.http.response.error.PasswordErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static cinema.Main.inMemory;

@RestController

public class StatsController {
    private final String PASSWORD = "super_secret";
    @RequestMapping(value = "/stats")
    @ResponseBody
    public ResponseEntity stats(
            @RequestParam(required = false) String password
    ) {
        if(password == null ||
           !password.equals(PASSWORD)
        ){
            PasswordErrorResponse passwordErrorResponse = new PasswordErrorResponse("The password is wrong!");
            HashMap<String,String> body = new HashMap<>();
            body.put("error", passwordErrorResponse.getMessage());
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(inMemory.getStats(), HttpStatus.OK);
    }
}
