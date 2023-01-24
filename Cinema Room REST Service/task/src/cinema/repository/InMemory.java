package cinema.repository;

import cinema.utils.RoomUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InMemory {

    public static InMemory instance;
    int[][] map = buildMap();
    HashMap<String, String> tokens = new HashMap<>();
    public InMemory InMemory(InMemory instance) {
        if(instance == null) {
            return this;
        }

        return instance;
    }

    public static InMemory getInstance() {
        return instance;
    }

    public HashMap<String, String> getTokens() {
        return tokens;
    }

    public int[][] getMap() {
        return map;
    }

    public int[][] buildMap() {
        int[][] arr = new int[9][9];
        for (int row = 1; row < arr.length; row++) {
            for (int column = 1; column < arr[row].length; column++) {
                arr[row][column] = 0;
            }
        }

        return arr;
    }

    public HashMap<String, Object> getStats() {
        HashMap<String, Object> stats = new HashMap<>();
        stats.put("number_of_available_seats", getAvailableSeats());
        stats.put("number_of_purchased_tickets", getPurchasedTickets());
        stats.put("current_income", incomeTickets());
        return stats;
    }

    public int getPurchasedTickets() {
        return tokens.size();
    }

    public int incomeTickets() {
        int income = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                if(map[row][column] == 1) {
                    income += RoomUtils.getPrice(row);
                }
            }
        }

        return income;
    }

    public int getAvailableSeats() {
        int availableSeats = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                if(map[row][column] == 0) {
                    availableSeats++;
                }
            }
        }

        return availableSeats;
    }

    public Integer[] findToken(String token) {
        for (Map.Entry<String, String> set : tokens.entrySet()) {
            if(!set.getValue().equals(token)) {
                continue;
            }
            String key = set.getKey();
            String[] splitted = key.split("-");
            String row = splitted[0];
            String column = splitted[1];
            int rowInt = Integer.parseInt(row);
            int columnInt = Integer.parseInt(column);
            int price = RoomUtils.getPrice(Integer.parseInt(row));
            System.out.println(map[rowInt][columnInt]);
            map[rowInt][columnInt] = 0;
            System.out.println(map[rowInt][columnInt]);
            return new Integer[]{
                    rowInt,
                    columnInt,
                    price
            };
        }

        return null;
    }

    public void removeToken(Integer row, Integer column) {
        tokens.remove(String.valueOf(row) + "-" + String.valueOf(column));
    }
}
