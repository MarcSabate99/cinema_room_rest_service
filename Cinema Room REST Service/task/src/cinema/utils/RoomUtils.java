package cinema.utils;

public class RoomUtils {
    public static final int ROWS = 9;
    public static final int SEATS_PER_ROW = 9;
    public static final int TOTAL_SEATS = ROWS * SEATS_PER_ROW;

    public static int getPrice(int row) {
        return row <= 4 ? 10: 8;
    }
}
