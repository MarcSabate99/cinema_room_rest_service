package cinema;

import cinema.repository.InMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static InMemory inMemory;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        inMemory  = new InMemory();
    }
}
